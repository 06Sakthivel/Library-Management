package com.poject.libraryManagement1.Service;

import com.poject.libraryManagement1.Exception.ApiRequestException;
import com.poject.libraryManagement1.Repository.UserRepository;
import com.poject.libraryManagement1.model.AppUser;
import com.poject.libraryManagement1.model.UserDetails;
import com.poject.libraryManagement1.model.ResponseMessage;
import com.poject.libraryManagement1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BookService bs;

    @Autowired
    private ResponseMessage message;

    @Override
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
        if(!validUser(user)){
            throw new ApiRequestException("User fields should not be null " + user);
        }
        return repository.save(user);
    }

    @Override
    public boolean validUser(User user){
        if(user.getEmail() == null || user.getFirstName() == null || user.getPassword() == null || user.getAppUser() == null){
            return false;
        }
        return true;
    }

    @Override
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
        if(!user.getPassword().equals(details.getPassword())){
            throw new ApiRequestException("Invalid mail or password");
        }
        return message;
    }

    @Override
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

    @Override
    public List<User> getUsers(String key){
        List<User> allUsers = new ArrayList<>();
        try{
            User user = repository.findById(key).get();
            if(user.getAppUser() != AppUser.ADMIN) throw new ApiRequestException("only admin can access user details");
            if(user.getAppUser() == AppUser.ADMIN && user.isLoggedIn()) {
                allUsers = repository.findAll();
            }
        } catch (Exception e){
            throw new ApiRequestException("Admin not found with the key " + key);
        }
        return allUsers;
    }

    @Override
    public String deleteUser(String id, String key){
        boolean isAdmin = bs.isAdmin(key);
        if(!isAdmin){
            throw new ApiRequestException("only admin can delete users");
        }
        try{
             repository.findById(id).get();
        } catch (Exception e){
            throw new ApiRequestException("no user found with the given id:" + id);
        }
        repository.deleteById(id);

        return "user deleted successfully";
    }

}
