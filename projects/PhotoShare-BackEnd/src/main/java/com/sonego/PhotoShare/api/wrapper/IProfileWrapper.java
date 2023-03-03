package com.sonego.PhotoShare.api.wrapper;

import com.sonego.PhotoShare.api.model.ProfileModel;
import com.sonego.PhotoShare.api.model.input.ProfileInput;
import com.sonego.PhotoShare.business.model.Profile;

import java.io.IOException;

public interface IProfileWrapper {

    public Profile toProfile(ProfileInput profileInput) throws IOException;

    public ProfileModel toModel(Profile profile);
}
