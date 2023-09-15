package com.khkim.basic.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArguementExceptionHandler(Exception exception){
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 입력");
    }

    // @ExceptionHandler(HttpMessageNotValidException.class)
    // public ResponseEntity<String> HTTPMessageNotValidExceptionHandler(Exception exception){
    //     exception.printStackTrace();
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("미 입력");
    // }
    
}
