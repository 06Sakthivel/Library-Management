package com.poject.libraryManagement1.Service;

import com.poject.libraryManagement1.Exception.ApiRequestException;
import com.poject.libraryManagement1.Repository.LibraryRepository;
import com.poject.libraryManagement1.Repository.UserRepository;
import com.poject.libraryManagement1.Service.model.AppUser;
import com.poject.libraryManagement1.Service.model.Book;
import com.poject.libraryManagement1.Service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService{

    @Autowired
    private LibraryRepository repository;

//    @Autowired
//    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    // create
    public Book addBook(Book book, String key){
        if(!isAdmin(key)){
            throw new ApiRequestException("only admin can add books to the library");
        }
        return repository.save(book);
    }

    // read

    public List<Book> getAllBooks(String key){

        User user = userRepository.findById(key).get();
        if (user == null) throw new ApiRequestException("not a valid user");
        List<Book> books = repository.findAll();
        if(user.isLoggedIn() && !books.isEmpty()){
            return books;
        } else if(!user.isLoggedIn()){
            throw new ApiRequestException("user must log-in inorder to access books from the library");
        }
        throw new ApiRequestException("no books found");
    }

    public Book getBookById(String id,String key){
        if(!repository.findById(id).isPresent()){
            throw new ApiRequestException("book is not available");
        }
        return repository.findById(id).get();
    }

    public List<Book> getBookByName(String name){
        return repository.findByName(name);
    }

    public boolean isAdmin(String key){
        User user = userRepository.findById(key).get() ;
        if(user!=null &&user.getAppUser() == AppUser.ADMIN && user.isLoggedIn()) {
            return true;
        }
        return false;
    }

    //update

    public Book updateBook(Book updatedBook, String key){

        if(!isAdmin(key)){
            throw new ApiRequestException("admin can only update books");
        }
        Book oldBook = repository.findById(updatedBook.getId()).get();
        oldBook.updateBook(updatedBook);
//        oldBook.setName(updatedBook.getName());
//        oldBook.setAuthor(updatedBook.getAuthor());
        return repository.save(oldBook);
    }

    // delete
    public String deleteBook(String id, String key){
        if(!isAdmin(key)){
            return "Only admin can delete";
        }
        repository.deleteById(id);
        return "book removed from the library";
    }

}
