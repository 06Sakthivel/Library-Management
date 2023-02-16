package com.poject.libraryManagement1.Service;

import com.poject.libraryManagement1.model.Book;

import java.util.List;

public interface BookService {
    public Book addBook(Book book, String key);

    public boolean isValid(Book book);

    public List<Book> getAllBooks(String key);

    public Book getBookById(String id,String key);

    public List<Book> getBookByName(String name);

    public boolean isAdmin(String key);

    public Book updateBook(Book updatedBook, String key);

    public String deleteBook(String id, String key);
}
