package com.sonego.PhotoShare.api.wrapper;

import com.sonego.PhotoShare.api.model.ProfileModel;
import com.sonego.PhotoShare.api.model.input.ProfileInput;
import com.sonego.PhotoShare.business.model.Profile;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@AllArgsConstructor
@Component
public class ProfileWrapper implements IProfileWrapper {

    private ModelMapper mapper;

    private IImageWrapper imageWrapper;

    @Override
    public Profile toProfile(ProfileInput profileInput) throws IOException {
        Profile profile = mapper.map(profileInput, Profile.class);
        profile.setProfileImage(imageWrapper.toImage(profileInput.getProfileImage()));
        return profile;
    }

    @Override
    public ProfileModel toModel(Profile profile) {
        return mapper.map(profile, ProfileModel.class);
    }
}
