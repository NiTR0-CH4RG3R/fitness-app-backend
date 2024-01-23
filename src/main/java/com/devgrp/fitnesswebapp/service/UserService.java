package com.devgrp.fitnesswebapp.service;

import com.devgrp.fitnesswebapp.dto.UserDTO;
import com.devgrp.fitnesswebapp.dto.UserGetDTO;
import com.devgrp.fitnesswebapp.entity.User;
import com.devgrp.fitnesswebapp.repository.UserRepository;
import com.devgrp.fitnesswebapp.util.VarList;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    public String createUser(UserDTO userDTO){
        if(userRepository.existsByEmail(userDTO.getEmail())){
            return VarList.RSP_DUPLICATED;
        }
        else{
            userRepository.save(modelMapper.map(userDTO,User.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateUser(UserDTO userDTO){
        if(userRepository.existsByEmail(userDTO.getEmail())){
            userRepository.save(modelMapper.map(userDTO,User.class));
            return VarList.RSP_SUCCESS;
        }
        else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<UserGetDTO> getAllUsers(){
        List<User> userList=userRepository.findAll();
        return modelMapper.map(userList,new TypeToken<ArrayList<UserGetDTO>>(){}.getType());
    }
    public UserGetDTO searchUser(String userEmail){
        if(userRepository.existsByEmail(userEmail)){
            User user=userRepository.findUserByEmail(userEmail).orElse(null);
            return modelMapper.map(user,UserGetDTO.class);
        }
        else {
            return null;
        }
    }
    public String deleteUser( String userEmail){
        if(userRepository.existsByEmail(userEmail)){
            userRepository.deleteByEmail(userEmail);
            return VarList.RSP_SUCCESS;
        }
        else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
