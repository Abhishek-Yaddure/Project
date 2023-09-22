package com.project.project.controller;

import com.project.project.model.User;
import com.project.project.service.CustomUserService;
import com.project.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserService customUserService;

    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int userid){
        return new ResponseEntity<>(userService.getUserById(userid), HttpStatus.CREATED);
    }

    @GetMapping("/get/{email}")
    public User getByEmail(@PathVariable String email){
        return (userService.getUserByEmail(email));
    }

    @PutMapping("/update/{id}")
    public User update(@RequestBody User user,@PathVariable("id") int userid){
        return userService.updateUser(user,userid);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int userid){
        userService.daleteUser(userid);
        return "user deleted sucessfully";
    }
}
