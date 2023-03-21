package com.sonego.PhotoShare.business.service;

import com.sonego.PhotoShare.business.exceptions.ForbiddenActionException;
import com.sonego.PhotoShare.business.exceptions.NotFoundException;
import com.sonego.PhotoShare.business.model.*;
import com.sonego.PhotoShare.persistence.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostService implements IPostService {

    private PostRepository postRepository;

    private IImageService imageService;

    private IProfileService profileService;

    private UserDetailsService userService;

    @Override
    @Transactional
    public Post createPost(String description, Image image, UUID profileId, String loggedUsername) throws IOException {
        User user = (User) userService.loadUserByUsername(loggedUsername);
        if (isUserProfileOwner(user, profileId))
            throw new ForbiddenActionException("You are not allowed to post in a profile that is not yours");
        Post post = new Post();
        post.setNewDescription(description);
        post.setImage(imageService.saveImage(image));
        post.setPostingDate(OffsetDateTime.now());
        postRepository.save(post);
        Profile profile = profileService.getProfileById(profileId);
        profile.addPost(post);
        return post;
    }

    @Override
    public Page<Post> feedWithPosts(int pageIndex, int pageSize) {
//        return postRepository.getRandomPosts(PageRequest.of(pageIndex, pageSize));
        return postRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    @Override
    public Post getPostById(UUID postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post of id " + postId + " could not be found"));
    }

    @Override
    public List<Post> listPostsByUser(UUID userId, String loggedUsername) {
        User user = (User) userService.loadUserByUsername(loggedUsername);
        if (!user.getId().equals(userId))
            throw new ForbiddenActionException("You are not allowed access another user's posts");
        List<Post> posts = new ArrayList<>();
        user.getProfiles().stream().map(profile -> posts.addAll(profile.getPosts()));
        return posts;
    }

    @Override
    @Transactional
    public Post setNewDescription(String description, UUID postId, String loggedUsername) {
        User user = (User) userService.loadUserByUsername(loggedUsername);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post of id " + postId + " could not be found"));
        if (!post.getProfile().getOwner().getId().equals(user.getId()))
            throw new ForbiddenActionException("You are not allowed to change " +
                    "the description of a post you did not do");

        post.setNewDescription(description);
        return post;
    }

    @Override
    @Transactional
    public Post commentPost(String content, UUID postId, String loggedUsername) {
        User user = (User) userService.loadUserByUsername(loggedUsername);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post of id " + postId + " could not be found"));
        post.addComment(content, user);
        return post;
    }

    private boolean isUserProfileOwner(User user, UUID profileId) {
        return !user.getProfiles().stream().anyMatch(profile -> profile.getId().equals(profileId));
    }
}
