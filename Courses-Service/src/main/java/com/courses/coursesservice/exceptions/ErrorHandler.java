package com.courses.coursesservice.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorClass> CustomErrorClass(CustomException ex){
        return new ResponseEntity<>( ErrorClass.builder()
                .status(false)
                .message(ex.getMessage())
                .build() , ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();
        StringBuilder builder = new StringBuilder();

        result.getFieldErrors().forEach(fieldError -> {
            builder.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("\n");
        });
        ErrorClass error = ErrorClass.builder()
                .status(false)
                .message(builder.toString())
                .build();
                return ResponseEntity.badRequest().body(error);
    }
}
