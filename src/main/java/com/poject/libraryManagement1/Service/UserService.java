package com.poject.libraryManagement1.Service;

import com.poject.libraryManagement1.Repository.LibraryRepository;
import com.poject.libraryManagement1.Repository.UserRepository;
import com.poject.libraryManagement1.model.AppUser;
import com.poject.libraryManagement1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
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

}
