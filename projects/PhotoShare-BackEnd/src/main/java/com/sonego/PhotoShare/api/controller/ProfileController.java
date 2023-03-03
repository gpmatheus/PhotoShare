package com.sonego.PhotoShare.api.controller;

import com.sonego.PhotoShare.api.model.ProfileModel;
import com.sonego.PhotoShare.api.model.input.ProfileInput;
import com.sonego.PhotoShare.api.wrapper.IProfileWrapper;
import com.sonego.PhotoShare.business.model.Profile;
import com.sonego.PhotoShare.business.service.IProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private IProfileWrapper profileWrapper;

    private IProfileService profileService;

    @GetMapping("/search")
    public ResponseEntity<Page<ProfileModel>> searchProfile(@RequestParam String search,
                                                           @RequestParam int pageSize,
                                                           @RequestParam int pageIndex) {
        Page<ProfileModel> profilesPage = profileService.search(search, pageSize, pageIndex)
                .map(profile -> {
                    ProfileModel profileModel = profileWrapper.toModel(profile);
                    profileModel.setAbout(null);
                    profileModel.setPosts(null);
                    return profileModel;
                });
        return ResponseEntity.ok(profilesPage);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileModel> getProfileById(@PathVariable UUID profileId) {
        ProfileModel profileModel = profileWrapper.toModel(profileService.getProfileById(profileId));
        profileModel.getPosts().stream()
                .forEach(post -> post.setProfile(null));
        return ResponseEntity.ok(profileModel);
    }

    @PostMapping
    public ResponseEntity<ProfileModel> createProfile(@ModelAttribute @Valid ProfileInput profileInput,
                                                      Principal principal) throws IOException {
        Profile profile = profileService.createProfile(profileWrapper.toProfile(profileInput), principal.getName());
        ProfileModel profileModel = profileWrapper.toModel(profile);
        profileModel.setPosts(null);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profileModel);
    }

    @PutMapping("/hide/{profileId}")
    public ResponseEntity<ProfileModel> hideProfile(@PathVariable UUID profileId, Principal principal) {
        return ResponseEntity
                .ok(profileWrapper.toModel(profileService.hideProfile(profileId, principal.getName())));
    }

    @PutMapping("/show/{profileId}")
    public ResponseEntity<ProfileModel> showProfile(@PathVariable UUID profileId, Principal principal) {
        return ResponseEntity
                .ok(profileWrapper.toModel(profileService.showProfile(profileId, principal.getName())));
    }
}
