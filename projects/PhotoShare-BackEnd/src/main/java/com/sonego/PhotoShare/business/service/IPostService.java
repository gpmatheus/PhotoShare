package com.sonego.PhotoShare.business.service;

import com.sonego.PhotoShare.business.model.Image;
import com.sonego.PhotoShare.business.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IPostService {

    public Post createPost(String description, Image image, UUID profileId, String loggedUsername) throws IOException;

    public Page<Post> feedWithPosts(int pageIndex, int pageSize);

//    public Page<Post> listPosts(int pageSize, int pageIndex);
    public List<Post> listPostsByUser(UUID userId, String loggedUsername);

    public Post setNewDescription(String description, UUID postId, String loggedUsername);

    public Post commentPost(String content, UUID postId, String loggedUsername);
}
