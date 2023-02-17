package com.poject.libraryManagement1.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
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
        if(other.name != null && !"".equals(other.name)){
             this.name = other.name;
        }
        if(other.author != null && !"".equals(other.author)){
            this.author = other.author;
        }
        if(other.genre != null && !"".equals(other.genre)){
            this.genre = other.genre;
        }
        if(other.price != null && !"".equals(other.price)){
            this.price = other.price;
        }
    }

}
