package com.project.project.service;

import com.project.project.dto.UserDto;
import com.project.project.exception.BadCredentialException;
import com.project.project.exception.BusinessException;
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
            throw new BadCredentialException("501","please enter the name");
        }
        if(userDto.getGender().isEmpty()||userDto.getGender().length()== 0){
            throw new BadCredentialException("502","please enter the proper gender");
        }
        if(userDto.getEmail().isEmpty()){
            throw new BadCredentialException("503","please enter the proper email");
        }
        if(userDto.getDate_of_brith().isEmpty()||userDto.getDate_of_brith().length()==0){
            throw new BadCredentialException("503","please enter the proper Date of Birth");
        }
        if(userDto.getAddress().isEmpty()||userDto.getAddress().length()==0){
            throw new BadCredentialException("504","please enter the proper Address");
        }

            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User user = modelMapper.map(userDto ,User.class);
            User saveUser=userRepository.save(user);
            return modelMapper.map(saveUser,UserDto.class);

//        catch (IllegalArgumentException e) {
//            throw new BusinessException("506","Give Proper Input"+e.getMessage());
//        }catch (Exception e) {
//            throw new BusinessException("507","something went wrong in service layer,while saving students"+e.getMessage());
//        }
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(int userid){
        return userRepository.findById(userid).get();
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
