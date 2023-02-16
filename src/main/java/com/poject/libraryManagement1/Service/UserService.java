package com.poject.libraryManagement1.Service;

import com.poject.libraryManagement1.model.ResponseMessage;
import com.poject.libraryManagement1.model.User;
import com.poject.libraryManagement1.model.UserDetails;

import java.util.List;

public interface UserService {
    public User register(User user);

    public boolean validUser(User user);

    public ResponseMessage userLogin(UserDetails details);

    public ResponseMessage userLogOut(UserDetails details);

    public List<User> getUsers(String key);

    public String deleteUser(String id, String key);
}
