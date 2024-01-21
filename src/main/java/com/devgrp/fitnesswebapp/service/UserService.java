package com.devgrp.fitnesswebapp.service;

import com.devgrp.fitnesswebapp.dto.UserDTO;
import com.devgrp.fitnesswebapp.entity.User;
import com.devgrp.fitnesswebapp.repository.UserRepository;
import com.devgrp.fitnesswebapp.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    public String createUser(UserDTO userDTO){
        if(userRepository.existsById(userDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }
        else{
            userRepository.save(modelMapper.map(userDTO,User.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateUser(UserDTO userDTO){
        if(userRepository.existsById(userDTO.getId())){
            userRepository.save(modelMapper.map(userDTO,User.class));
            return VarList.RSP_SUCCESS;
        }
        else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<UserDTO> getAllUsers(){
        List<User> userList=userRepository.findAll();
        return modelMapper.map(userList,new TypeToken<ArrayList<UserDTO>>(){}.getType());
    }
    public UserDTO searchUser(int userID){
        if(userRepository.existsById(userID)){
            User user=userRepository.findById(userID).orElse(null);
            return modelMapper.map(user,UserDTO.class);
        }
        else {
            return null;
        }
    }
    public String deleteUser( int userID){
        if(userRepository.existsById(userID)){
            userRepository.deleteById(userID);
            return VarList.RSP_SUCCESS;
        }
        else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
