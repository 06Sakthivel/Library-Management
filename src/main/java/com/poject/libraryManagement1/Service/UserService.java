package com.poject.libraryManagement1.Service;

import com.poject.libraryManagement1.Repository.UserRepository;
import com.poject.libraryManagement1.model.AppUser;
import com.poject.libraryManagement1.model.UserDetails;
import com.poject.libraryManagement1.model.ResponseMessage;
import com.poject.libraryManagement1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ResponseMessage message;
    public User register(User user){
        if(user.getAppUser() == AppUser.ADMIN){
            User adminExist = repository.findByAppUser(user.getAppUser());
            if(adminExist != null) {
                throw new IllegalStateException("admin already exists");
            }
        }
        User exists = repository.findByEmail(user.getEmail());
        if(exists != null){
            throw new IllegalStateException("email already taken");
        }
        return repository.save(user);
    }

    public ResponseMessage userLogin(UserDetails details){
        User user = repository.findByEmail(details.getEmail());
        if(user == null){
            return message;
        }
        boolean isLoggedIn = user.isLoggedIn();
        if(isLoggedIn) {
            message.setId(user.getId());
            message.setMessage("already logged-in");
        }
        if(!isLoggedIn && user.getPassword().equals(details.getPassword())){
            user.setLoggedIn(true);
            message.setId(user.getId());
            message.setMessage("logged in successfully");
            repository.save(user);
        }
        return message;
    }

    public ResponseMessage userLogOut(UserDetails details){
        User user = repository.findByEmail(details.getEmail());
        if(user == null){
            return message;
        }
        boolean isLoggedIn = user.isLoggedIn();
        if(!isLoggedIn) {
            message.setId(user.getId());
            message.setMessage("user must log-in");
        }
        if(isLoggedIn && user.getPassword().equals(details.getPassword())){
            user.setLoggedIn(false);
            message.setId(user.getId());
            message.setMessage("logged out successfully");
            repository.save(user);
        }
        return message;
    }



    public List<User> getUsers(String key, String email){
        User user = repository.findById(key).get() ;
        if(user!=null &&user.getAppUser() == AppUser.ADMIN && user.isLoggedIn()) {
            return repository.findAll();
        }
        User user1 = repository.findByEmail(email);
        if(user1!=null && user1.getAppUser() == AppUser.ADMIN && user1.isLoggedIn()) {
            return repository.findAll();
        }
        return null;
    }

}
