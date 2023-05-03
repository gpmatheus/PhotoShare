package com.sonego.PhotoShare.api.controller;

import com.sonego.PhotoShare.api.model.PostModel;
import com.sonego.PhotoShare.api.model.input.PostInput;
import com.sonego.PhotoShare.api.wrapper.IImageWrapper;
import com.sonego.PhotoShare.api.wrapper.PostWrapper;
import com.sonego.PhotoShare.business.model.Image;
import com.sonego.PhotoShare.business.model.Post;
import com.sonego.PhotoShare.business.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private PostWrapper postWrapper;
    private IPostService postService;

    private IImageWrapper imageWrapper;

    @PostMapping
    public ResponseEntity<PostModel> createPost(@ModelAttribute @Valid PostInput postInput,
                                                Principal principal) throws Exception {
        Image image = imageWrapper.toImage(postInput.getImage());
        Post post = postService.createPost(postInput.getDescription(),
                image, postInput.getProfileId(), principal.getName());
        PostModel postModel = postWrapper.toModel(post);
        postModel.getProfile().setPosts(null);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postModel);
    }

    @GetMapping
    public ResponseEntity<Page<PostModel>> feedWithPosts(@RequestParam int pageSize,
                                                         @RequestParam int pageIndex) {
        Page<PostModel> postModelPage = postService.feedWithPosts(pageIndex, pageSize)
                .map(post -> {
                    PostModel postModel = postWrapper.toModel(post);
                    postModel.getProfile().setPosts(null);
                    return postModel;
                });
        return ResponseEntity.ok(postModelPage);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostModel> getPostById(@PathVariable UUID postId) {
        Post post = postService.getPostById(postId);
        PostModel postModel = postWrapper.toModel(post);
        postModel.getProfile().setPosts(null);
        return ResponseEntity.ok(postModel);
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<PostModel>> listPostsByUser(@PathVariable UUID userId,
                                                           Principal principal) {
        List<Post> posts = postService.listPostsByUser(userId, principal.getName());
        List<PostModel> postModels = postWrapper.toModelCollection(posts);
        postModels.forEach(post -> post.setDescriptions(null));
        return ResponseEntity.ok(postModels);
    }

    @PutMapping("/{postId}/changeDescription")
    public ResponseEntity<PostModel> setNewDescription(@RequestBody @NotBlank String newDescription,
                                                       @PathVariable UUID postId,
                                                       Principal principal) {
        Post post = postService.setNewDescription(newDescription, postId, principal.getName());
        PostModel postModel = postWrapper.toModel(post);
        postModel.getProfile().setPosts(null);
        return ResponseEntity.ok(postModel);
    }

    @PutMapping("/{postId}/comment")
    public ResponseEntity<PostModel> commentPost(@RequestBody @NotBlank String comment,
                                                 @PathVariable UUID postId,
                                                 Principal principal) {
        Post post = postService.commentPost(comment, postId, principal.getName());
        PostModel postModel = postWrapper.toModel(post);
        postModel.getProfile().setPosts(null);
        return ResponseEntity.ok(postModel);
    }
}
