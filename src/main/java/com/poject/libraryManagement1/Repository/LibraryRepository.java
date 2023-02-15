package com.poject.libraryManagement1.Repository;

import com.poject.libraryManagement1.model.Book;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository
        extends MongoRepository<Book, String> {
    List<Book> findByName(String name);


}
