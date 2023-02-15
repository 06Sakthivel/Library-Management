package com.poject.libraryManagement1.Service;

import com.poject.libraryManagement1.Exception.ApiRequestException;
import com.poject.libraryManagement1.Repository.UserRepository;
import com.poject.libraryManagement1.Service.model.AppUser;
import com.poject.libraryManagement1.Service.model.UserDetails;
import com.poject.libraryManagement1.Service.model.ResponseMessage;
import com.poject.libraryManagement1.Service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BookService bs;

    @Autowired
    private ResponseMessage message;
    public User register(User user){
        if(user.getAppUser() == AppUser.ADMIN){
            User adminExist = repository.findByAppUser(user.getAppUser());
            if(adminExist != null) {
                throw new ApiRequestException("admin already exists");
            }
        }
        User exists = repository.findByEmail(user.getEmail());
        if(exists != null){
            throw new ApiRequestException("email already taken");
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



    public List<User> getUsers(String key){
        User user = repository.findById(key).get() ;
        if(user!=null &&user.getAppUser() == AppUser.ADMIN && user.isLoggedIn()) {
            return repository.findAll();
        }

        throw new ApiRequestException("no user found");
    }

    public String deleteUser(String id, String key){
        boolean isAdmin = bs.isAdmin(key);
        if(!isAdmin){
            throw new ApiRequestException("only admin can delete users");
        }

        User user = repository.findById(id).get();
        if(user==null) {
            throw new ApiRequestException("no user found with the giver id:" + id);
        }

        repository.deleteById(id);

        return "user deleted successfully";
    }

}
