package com.project.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(BadCredentialException.class)
    public ResponseEntity<String> handleBadcradentialException(){
        return new ResponseEntity<>("please give proper input", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String>handleNosuchArgumentException(){
        return new ResponseEntity<>("No value present in DB with this Id,please change your request",HttpStatus.BAD_REQUEST);
    }
}


