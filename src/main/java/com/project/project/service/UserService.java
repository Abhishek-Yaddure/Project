package com.project.project.service;

import com.project.project.dto.UserDto;
import com.project.project.exception.BadCredentialException;
import com.project.project.model.User;
import com.project.project.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public UserDto createUser(UserDto userDto){
        if(userDto.getName().isEmpty()||userDto.getName().length()==0){
            throw new BadCredentialException("501","please give proper input");
        }

      userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto ,User.class);
        User saveUser=userRepository.save(user);
        return modelMapper.map(saveUser,UserDto.class);

    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(int userid){
        return userRepository.findById(userid).orElseThrow(()->new RuntimeException("User Not found"));
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found By This Email"));
    }

    public User updateUser(User user,int userid){
        User existingUser = userRepository.findById(userid).orElseThrow(()->new RuntimeException("User Not Found"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAddress(user.getAddress());
        existingUser.setMobile_no(user.getMobile_no());
        existingUser.setGender(user.getGender());
        existingUser.setDate_of_brith(user.getDate_of_brith());

        return  userRepository.save(existingUser);
    }

    public void daleteUser(int userid){
        userRepository.findById(userid).orElseThrow(()->new RuntimeException("user Not Found"));
        userRepository.deleteById(userid);
    }


}
