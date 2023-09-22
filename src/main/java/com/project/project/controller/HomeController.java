package com.project.project.controller;

import com.project.project.model.User;
import com.project.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int userid){
        return new ResponseEntity<>(userService.getUserById(userid), HttpStatus.CREATED);
    }
}
