package com.poject.libraryManagement1.Service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document
@AllArgsConstructor
@ToString
public class Book {
    @Id
    private String id;
    private String name;
    private String author;
    private String genre;
    private String price;

    public void updateBook(Book other) {
        this.id = other.id;
        this.name = other.name;
        this.author = other.author;
        this.genre = other.genre;
        this.price = other.price;
    }
}
