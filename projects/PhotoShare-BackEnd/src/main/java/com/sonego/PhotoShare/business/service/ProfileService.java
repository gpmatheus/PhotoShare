package com.sonego.PhotoShare.business.service;

import com.sonego.PhotoShare.business.exceptions.BusinessException;
import com.sonego.PhotoShare.business.exceptions.NotFoundException;
import com.sonego.PhotoShare.business.model.Profile;
import com.sonego.PhotoShare.business.model.User;
import com.sonego.PhotoShare.persistence.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileService implements IProfileService {

    private ProfileRepository profileRepository;

    private UserService userService;

    private IImageService imageService;

    @Override
    public Page<Profile> search(String nameString, int pageSize, int pageIndex) {
        return profileRepository.search(
                nameString,
                PageRequest.of(
                        pageIndex,
                        pageSize,
                        Sort.Direction.ASC,
                        "name"
                )
        );
    }

    @Override
    public Profile getProfileById(UUID id) {
        return profileRepository.findById(id).orElseThrow(() -> new NotFoundException("Profile with id " +
                id + " could not be found"));
    }

    @Override
    @Transactional
    public Profile createProfile(Profile profile, String loggedUsername) throws IOException {
        if (profileRepository.findByName(profile.getName()).isPresent())
            throw new BusinessException("There is already a profile called '" + profile.getName() + "'");
        User user = userService.getByUsername(loggedUsername);
        profile.setOwner(user);
        profile.setProfileImage(imageService.saveImage(profile.getProfileImage()));
        return profileRepository.save(profile);
    }

    @Override
    @Transactional
    public Profile hideProfile(UUID id, String loggedUsername) {
        User user = userService.getByUsername(loggedUsername);
        Profile profile = getProfileFromUser(user, id);
        if (!profile.isVisible())
            throw new BusinessException("The profile of id " + id + " is already hidden");
        profile.setVisible(false);
        return profile;
    }

    @Override
    @Transactional
    public Profile showProfile(UUID id, String loggedUsername) {
        User user = userService.getByUsername(loggedUsername);
        Profile profile = getProfileFromUser(user, id);
        if (profile.isVisible())
            throw new BusinessException("The profile of id " + id + " is already visible");
        profile.setVisible(true);
        return profile;
    }

    private Profile getProfileFromUser(User user, UUID profileId) {
        return user.getProfiles().stream().filter(p -> p.getId().equals(profileId))
                .findFirst().orElseThrow(() -> new NotFoundException("Profile with id " +
                        profileId + " could not be found"));
    }
}
