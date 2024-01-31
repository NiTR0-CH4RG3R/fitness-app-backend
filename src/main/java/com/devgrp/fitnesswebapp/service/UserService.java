package com.devgrp.fitnesswebapp.service;

import com.devgrp.fitnesswebapp.dto.GoalDTO;
import com.devgrp.fitnesswebapp.dto.GoalGetDTO;
import com.devgrp.fitnesswebapp.dto.UserDTO;
import com.devgrp.fitnesswebapp.dto.UserGetDTO;
import com.devgrp.fitnesswebapp.entity.Goal;
import com.devgrp.fitnesswebapp.entity.User;
import com.devgrp.fitnesswebapp.repository.GoalRepository;
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
    @Autowired
    private GoalRepository goalRepository;
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
    public String addGoal(String userEmail,GoalDTO goalDTO) {
        try {
            if (userRepository.existsByEmail(userEmail)){
                User user=userRepository.findUserByEmail(userEmail).orElse(null);
                Goal goal=modelMapper.map(goalDTO, Goal.class);
                assert user != null;
                if(user.getGoals()==null){
                    ArrayList<Goal> goals= new ArrayList<>();
                    goals.add(goal);
                }
                else {
                    user.getGoals().add(user.getGoals().size(),goal);
                }
                userRepository.save(user);
                return VarList.RSP_SUCCESS;
            } else {
                return VarList.RSP_ERROR;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public List<GoalGetDTO> getAllGoals(String userEmail){
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if ( user == null ) return null;
        List<Goal> goalList = user.getGoals();
        if ( goalList == null ) return null;
        return modelMapper.map( goalList, new TypeToken<ArrayList<GoalGetDTO>> () {}.getType());
    }
    public GoalGetDTO searchGoal(String userEmail,String goalName){
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if ( user == null ) return null;
        List<Goal> goalList = user.getGoals();
        for(int i=0;i<goalList.size();i++){
            if(goalList.contains(goalName))
                //[TODO]:complete by getting from the goalDTO
        }
    }
}
