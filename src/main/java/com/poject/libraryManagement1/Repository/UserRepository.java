package com.poject.libraryManagement1.Repository;

import com.poject.libraryManagement1.model.AppUser;
import com.poject.libraryManagement1.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String > {

    User findByEmail(String email);

    User findByAppUser(AppUser user);
}
