package com.poject.libraryManagement1.Service;

import com.poject.libraryManagement1.Exception.ApiRequestException;
import com.poject.libraryManagement1.Repository.LibraryRepository;
import com.poject.libraryManagement1.Repository.UserRepository;
import com.poject.libraryManagement1.model.AppUser;
import com.poject.libraryManagement1.model.Book;
import com.poject.libraryManagement1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private LibraryRepository repository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Book addBook(Book book, String key){
        if(!isAdmin(key)){
            throw new ApiRequestException("only admin can add books to the library");
        }
        if(!isValid(book)){
            throw new ApiRequestException("book details should not be empty");
        }
        return repository.save(book);
    }

    @Override
    public boolean isValid(Book book){
        if(book.getName() == null || book.getAuthor() == null || book.getPrice() == null || book.getGenre() == null){
            return false;
        }
        return true;
    }


    @Override
    public List<Book> getAllBooks(String key){
        User user;
        try{
            user = userRepository.findById(key).get();
        } catch (Exception e){
            throw new ApiRequestException("not a valid user or register to use the library");
        }

        List<Book> books = repository.findAll();
        if(user.isLoggedIn() && !books.isEmpty()){
            return books;
        } else if(!user.isLoggedIn()){
            throw new ApiRequestException("user must log-in inorder to access books from the library");
        }
        throw new ApiRequestException("no books found");
    }

    @Override
    public Book getBookById(String id,String key){

        try{
            userRepository.findById(key);
        } catch (Exception e){
            throw new ApiRequestException("Not a valid user");
        }
        if(!repository.findById(id).isPresent()){
            throw new ApiRequestException("book is not available");
        }
        return repository.findById(id).get();
    }

    @Override
    public List<Book> getBookByName(String name){
        return repository.findByName(name);
    }

    @Override
    public boolean isAdmin(String key){
        User user;
        try{
             user = userRepository.findById(key).get() ;
            if(user.getAppUser() == AppUser.ADMIN && user.isLoggedIn()) {
                return true;
            }
        } catch (Exception e){
            throw new ApiRequestException("Login as admin or check the authentication key " + key);
        }
        return false;
    }

    @Override
    public Book updateBook(Book updatedBook, String key){

        if(!isAdmin(key)){
            throw new ApiRequestException("admin can only update books");
        }
        Book oldBook = repository.findById(updatedBook.getId()).get();
        oldBook.updateBook(updatedBook);
        return repository.save(oldBook);
    }


    @Override
    public String deleteBook(String id, String key){
        if(!isAdmin(key)){
            return "Only admin can delete";
        }
        repository.deleteById(id);
        return "book removed from the library";
    }

}
