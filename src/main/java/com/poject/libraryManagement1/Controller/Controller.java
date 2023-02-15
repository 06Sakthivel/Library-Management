package com.poject.libraryManagement1.Controller;

import com.poject.libraryManagement1.Repository.UserRepository;
import com.poject.libraryManagement1.Service.BookService;
import com.poject.libraryManagement1.Service.UserService;
import com.poject.libraryManagement1.Service.model.Book;
import com.poject.libraryManagement1.Service.model.UserDetails;
import com.poject.libraryManagement1.Service.model.ResponseMessage;
import com.poject.libraryManagement1.Service.model.User;
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
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user){
       return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseMessage login(@RequestBody UserDetails details){
        return userService.userLogin(details);
    }

    @PostMapping("/logout")
    public ResponseMessage logout(@RequestBody UserDetails details){
        return userService.userLogOut(details);
    }

    @PostMapping("/addbook")
    public Book addBook(@RequestBody Book book,@RequestHeader("authenticationKey") String key){
        return bookService.addBook(book, key);
    }

    @GetMapping("/users")
    public List<User> getUsers(@RequestHeader("authenticationKey") String key){
        return userService.getUsers(key);
    }

    @GetMapping("/books")
    public List<Book> getBooks(@RequestHeader("authenticationKey") String key){
        return bookService.getAllBooks(key);
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") String id,
                        @RequestHeader("authenticationKey") String key) {

        return bookService.getBookById(id, key);
    }

//    @GetMapping("/book/{name}")
//    public List<Book> getBookByName(@PathVariable("name") String name, @RequestHeader("authenticationKey") String key){
//        return bookService.getBookByName(name);
//    }

    @PutMapping("/updatebook")
    public Book updateBook(@RequestBody Book book,
                           @RequestHeader("authenticationKey") String key
                           ){
        return bookService.updateBook(book, key);
    }

    @DeleteMapping("/book/remove/{id}")
    public String removeBook(@PathVariable("id") String id,
                             @RequestHeader("authenticationKey") String key){
        return bookService.deleteBook(id, key);
    }

    @DeleteMapping("/user/delete")
    public String deleteUser(@RequestParam("id") String id, @RequestHeader("authenticationKey") String key){
        return userService.deleteUser(id, key);
    }

}
