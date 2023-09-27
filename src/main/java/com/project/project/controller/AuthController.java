package com.project.project.controller;


import com.project.project.dto.UserDto;
import com.project.project.entity.JwtRequest;
import com.project.project.entity.JwtResponce;
import com.project.project.exception.BusinessException;
import com.project.project.exception.ControllerException;
import com.project.project.model.User;
import com.project.project.security.JwtHelper;
import com.project.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtHelper helper;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponce> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponce jwtResponce = JwtResponce.builder()
                .jwtToken(token)
                .userName(userDetails.getUsername()).build();
        return new ResponseEntity<>(jwtResponce, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userdto){
//        try {
            return new ResponseEntity<>(userService.createUser(userdto),HttpStatus.CREATED)  ;
//        }catch (BusinessException e) {
//            ControllerException ce = new ControllerException(e.getErrorcode(),e.getErrormessage());
//            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
//        }catch (Exception e) {
//            ControllerException ce = new ControllerException("610","Something went wrong in controller layer");
//            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
//    }

}
}
