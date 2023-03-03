package com.sonego.PhotoShare.business.service;

import com.sonego.PhotoShare.business.model.Post;
import com.sonego.PhotoShare.business.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.UUID;

public interface IProfileService {

    public Page<Profile> search(String nameString, int pageSize, int pageIndex);

    public Profile getProfileById(UUID id);

    public Profile createProfile(Profile profile, String loggedUsername) throws IOException;

    public Profile hideProfile(UUID id, String loggedUsername);

    public Profile showProfile(UUID id, String loggedUsername);
}
