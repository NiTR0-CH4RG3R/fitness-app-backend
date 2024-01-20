package com.devgrp.fitnesswebapp.service;

import com.devgrp.fitnesswebapp.dto.UserDTO;
import com.devgrp.fitnesswebapp.entity.User;
import com.devgrp.fitnesswebapp.repository.UserRepository;
import com.devgrp.fitnesswebapp.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
