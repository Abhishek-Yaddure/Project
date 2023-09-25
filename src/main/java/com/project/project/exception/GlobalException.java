package com.project.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(BadCredentialException.class)
    public ResponseEntity<String> handleBadcradentialException(){
        return new ResponseEntity<>("please give proper Input", HttpStatus.BAD_REQUEST);
    }
}


