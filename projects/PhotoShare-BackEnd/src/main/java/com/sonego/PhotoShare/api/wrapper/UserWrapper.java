package com.sonego.PhotoShare.api.wrapper;

import com.sonego.PhotoShare.api.model.UserModel;
import com.sonego.PhotoShare.api.model.input.UserInput;
import com.sonego.PhotoShare.business.model.Profile;
import com.sonego.PhotoShare.business.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserWrapper implements IUserWrapper {

    private ModelMapper mapper;

    @Override
    public User toUser(UserInput userInput) {
        return mapper.map(userInput, User.class);
    }

    @Override
    public UserModel toModel(User user) {
        return mapper.map(user, UserModel.class);
    }
}
