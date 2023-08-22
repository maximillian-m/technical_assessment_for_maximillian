package com.students.studentservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends Exception{
    private HttpStatus status;
    public CustomException(){}
    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
