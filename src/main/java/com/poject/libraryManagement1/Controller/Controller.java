package com.poject.libraryManagement1.Controller;

import com.poject.libraryManagement1.Service.BookService;
import com.poject.libraryManagement1.Service.UserService;
import com.poject.libraryManagement1.model.Book;
import com.poject.libraryManagement1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public void login(@RequestHeader("authentication") String key ){
        System.out.println(key);
    }

    @PostMapping("/addbook")
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") String id) {

        return bookService.getBookById(id);
    }

//    @GetMapping("/book/{name}")
//    public List<Book> getBookByName(@PathVariable("name") String name){
//        return bookService.getBookByName(name);
//    }

    @PutMapping("/updatebook")
    public Book updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @DeleteMapping("/book/remove/{id}")
    public String removeBook(@PathVariable("id") String id){
        return bookService.deleteBook(id);
    }


}
