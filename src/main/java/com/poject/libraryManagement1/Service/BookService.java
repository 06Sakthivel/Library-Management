package com.poject.libraryManagement1.Service;

import com.poject.libraryManagement1.Repository.LibraryRepository;
import com.poject.libraryManagement1.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService{

    @Autowired
    private LibraryRepository repository;


    // create
    public Book addBook(Book book){

        return repository.save(book);
    }

    // read

    public List<Book> getAllBooks(){

        return repository.findAll();
    }

    public Book getBookById(String id){
        if(!repository.findById(id).isPresent()){
            return new Book("null", "null", null);
        }
        return repository.findById(id).get();
    }

    public List<Book> getBookByName(String name){

        return repository.findByName(name);
    }

    //update

    public Book updateBook(Book updatedBook ){
        Book oldBook = repository.findById(updatedBook.getId()).get();
        oldBook.setName(updatedBook.getName());
        oldBook.setAuthor(updatedBook.getAuthor());
        return repository.save(oldBook);
    }

    // delete
    public String deleteBook(String id){
        repository.deleteById(id);
        return "book removed from the library";
    }
}
