package com.sonego.PhotoShare.api.wrapper;

import com.sonego.PhotoShare.api.model.PostModel;
import com.sonego.PhotoShare.api.model.input.PostInput;
import com.sonego.PhotoShare.business.model.Post;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PostWrapper implements IPostWrapper {

    private ModelMapper mapper;

    @Override
    public PostModel toModel(Post post) {
        return mapper.map(post, PostModel.class);
    }

    @Override
    public List<PostModel> toModelCollection(List<Post> posts) {
        return posts.stream().map(post -> toModel(post)).collect(Collectors.toList());
    }
}
