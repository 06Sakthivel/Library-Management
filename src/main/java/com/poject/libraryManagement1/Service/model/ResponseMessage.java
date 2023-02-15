package com.poject.libraryManagement1.Service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@Service
public class ResponseMessage {
    private String id;
    private String message;

    public ResponseMessage() {
        this.id = "null";
        this.message = "user not found";
    }
}
