package com.poject.libraryManagement1.Service;

import com.poject.libraryManagement1.Repository.LibraryRepository;
import com.poject.libraryManagement1.Repository.UserRepository;
import com.poject.libraryManagement1.model.AppUser;
import com.poject.libraryManagement1.model.Book;
import com.poject.libraryManagement1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService{

    @Autowired
    private LibraryRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    // create
    public Book addBook(Book book){

        return repository.save(book);
    }

    // read

    public List<Book> getAllBooks(String key){
        User user = userRepository.findById(key).get();
        if(user.isLoggedIn()){
          return repository.findAll();
        }
        return null;
    }

    public Book getBookById(String id,String key, String email){
        if(!repository.findById(id).isPresent()){
            return new Book("null", "null", null);
        }
        return repository.findById(id).get();
    }

    public List<Book> getBookByName(String name){

        return repository.findByName(name);
    }

    public boolean isAdmin(String key, String email){
        User user = userRepository.findById(key).get() ;
        if(user!=null &&user.getAppUser() == AppUser.ADMIN && user.isLoggedIn()) {
            return true;
        }
        User user1 = userRepository.findByEmail(email);
        if(user1!=null && user1.getAppUser() == AppUser.ADMIN && user1.isLoggedIn()) {
            return true;
        }
        return false;
    }

    //update

    public Book updateBook(Book updatedBook, String key, String email ){

        Book oldBook = repository.findById(updatedBook.getId()).get();
        oldBook.setName(updatedBook.getName());
        oldBook.setAuthor(updatedBook.getAuthor());
        return repository.save(oldBook);
    }

    // delete
    public String deleteBook(String id, String key, String email){
        repository.deleteById(id);
        return "book removed from the library";
    }
}
