package com.poject.libraryManagement1.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document(collection = "Books")
@AllArgsConstructor
@ToString
public class Book {
    @Id
    private String id;
    private String name;
    private String author;
//    private String pirce;

//    public Book(String id, String name, String author) {
//        this.id = id;
//        this.name = name;
//        this.author = author;
//    }

//    @Override
//    public String toString() {
//        return "Book{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", author='" + author + '\'' +
//                '}';
//    }


}
