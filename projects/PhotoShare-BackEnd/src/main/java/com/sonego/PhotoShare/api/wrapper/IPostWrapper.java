package com.sonego.PhotoShare.api.wrapper;

import com.sonego.PhotoShare.api.model.PostModel;
import com.sonego.PhotoShare.api.model.input.PostInput;
import com.sonego.PhotoShare.business.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IPostWrapper {

    public PostModel toModel(Post post);
    public List<PostModel> toModelCollection(List<Post> posts);
}
