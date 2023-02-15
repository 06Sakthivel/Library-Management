package com.poject.libraryManagement1.Controller;

import com.poject.libraryManagement1.Service.BookService;
import com.poject.libraryManagement1.Service.UserService;
import com.poject.libraryManagement1.model.Book;
import com.poject.libraryManagement1.model.UserDetails;
import com.poject.libraryManagement1.model.ResponseMessage;
import com.poject.libraryManagement1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/library")
public class Controller {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user){
       return userService.register(user);
    }


    @PostMapping("/login")
    public ResponseMessage login(@RequestBody UserDetails details){
        return userService.userLogin(details);
    }

    @PostMapping("/logout")
    public ResponseMessage logout(@RequestBody UserDetails user){
        return userService.userLogOut(user);
    }

    @PostMapping("/addbook")
    public Book addBook(@RequestBody Book book,@RequestHeader("authenticationKey") String key, @RequestHeader("email") String email){

        return bookService.addBook(book, key, email);
    }

    @GetMapping("/users")
    public List<User> getUsers(@RequestHeader("authenticationKey") String key,
                               @RequestHeader("email") String email){

        return userService.getUsers(key, email);
    }

    @GetMapping("/books")
    public List<Book> getBooks(@RequestHeader("authenticationKey") String key){
        return bookService.getAllBooks(key);
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") String id,
                        @RequestHeader("authenticationKey") S
                                tring key, @RequestHeader("email") String email) {

        return bookService.getBookById(id, key, email);
    }

//    @GetMapping("/book/{name}")
//    public List<Book> getBookByName(@PathVariable("name") String name){
//        return bookService.getBookByName(name);
//    }

    @PutMapping("/updatebook")
    public Book updateBook(@RequestBody Book book,
                           @RequestHeader("authenticationKey") String key ,
                           @RequestHeader("email") String email){
        return bookService.updateBook(book, key, email);
    }

    @DeleteMapping("/book/remove/{id}")
    public String removeBook(@PathVariable("id") String id,
                             @RequestHeader("authenticationKey") String key,
                             @RequestHeader("email") String email){
        return bookService.deleteBook(id, key, email);
    }


}
