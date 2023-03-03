package com.sonego.PhotoShare.api.wrapper;

import com.sonego.PhotoShare.api.model.UserModel;
import com.sonego.PhotoShare.api.model.input.UserInput;
import com.sonego.PhotoShare.business.model.User;

public interface IUserWrapper {

    public User toUser(UserInput userInput);

    public UserModel toModel(User user);
}
