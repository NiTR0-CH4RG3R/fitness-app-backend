package com.devgrp.fitnesswebapp.controller;

import com.devgrp.fitnesswebapp.dto.*;
import com.devgrp.fitnesswebapp.service.UserService;
import com.devgrp.fitnesswebapp.util.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ResponseDTO responseDTO;
    @PatchMapping(value = "/add")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO){
        try{
            String res=userService.createUser(userDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully saved");
                responseDTO.setContent(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Allready added");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
            else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseDTO> getAllUsers(){
        try{
            List<UserGetDTO> userGetDTOList=userService.getAllUsers();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("success");
            responseDTO.setContent(userGetDTOList);
            return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/update")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO userDTO){
        try{
            String res=userService.updateUser(userDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully updated");//[TODO]:edit code to update user
                responseDTO.setContent(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("User Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/search/{userEmail}")
    public ResponseEntity<ResponseDTO> searchUser(@PathVariable String userEmail){
        UserGetDTO userGetDTO=userService.searchUser(userEmail);
        if(userGetDTO!=null){
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(userGetDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        }
        else{
            responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
            responseDTO.setMessage("User not Found");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(value = "/delete/{userEmail}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String userEmail){
        try{
            String res=userService.deleteUser(userEmail);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully deleted");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("User Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/addGoal")
    public ResponseEntity<ResponseDTO> addGoal(@RequestBody GoalUser goalUser){
        try{
            String res= userService.addGoal(goalUser.getUserEmail(),goalUser.getGoalDTO());
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Goal Successfully added");
                responseDTO.setContent(goalUser);
                return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
            }
            else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage(ex.getMessage());
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getAllGoals/{userEmail}")
    public ResponseEntity<ResponseDTO> getAllGoals(@PathVariable String userEmail) {
        try {
            List<GoalGetDTO> goalGetDTOList = userService.getAllGoals(userEmail);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("success");
            responseDTO.setContent(goalGetDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/updateGoal")
    public ResponseEntity<ResponseDTO> updateGoal(@RequestBody GoalUserEmail goalUserEmail){
        try{
            String res=userService.updateGoal(goalUserEmail.getUserEmail(),goalUserEmail.getGoalType(),goalUserEmail.getGoalDTO());
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully updated");//[TODO]:edit code to update user
                responseDTO.setContent(goalUserEmail.getGoalDTO());
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Goal Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/deleteGoal")
    public ResponseEntity<ResponseDTO> deleteGoal(@RequestBody GoalDelete goalDelete) {
        try {
            String res = userService.deleteGoal(goalDelete.getUseremail(),goalDelete.getGoalType());
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully deleted");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Goal Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //workout plan
    @PostMapping(value = "/addWorkoutPlan")
    public ResponseEntity<ResponseDTO> addWorkoutPlan(@RequestBody AddWorkoutPlan addWorkoutPlan){
        try{
            String res= userService.addWorkoutPlan(addWorkoutPlan.getUserEmail(),addWorkoutPlan.getWorkoutPlanDTO());
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Goal Successfully added");
                responseDTO.setContent(addWorkoutPlan);
                return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
            }
            else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getFollowededWorkoutPlans/{userEmail}")
    public ResponseEntity<ResponseDTO> getFollowedWorkoutPlan(@PathVariable String userEmail) {
        try {
            WorkoutPlanGetDTO workoutPlanGetDTO = userService.getFollowedWorkoutPlan(userEmail);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("success");
            responseDTO.setContent(workoutPlanGetDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getCreatedWorkoutPlans/{userEmail}")
    public ResponseEntity<ResponseDTO> getCreatedWorkoutPlans(@PathVariable String userEmail) {
        try {
            List<WorkoutPlanGetDTO> workoutPlanGetDTOList = userService.getCreatedWorkoutPlans(userEmail);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("success");
            responseDTO.setContent(workoutPlanGetDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/updateGoal")
    public ResponseEntity<ResponseDTO> updateGoal(@RequestBody GoalUserEmail goalUserEmail){
        try{
            String res=userService.updateGoal(goalUserEmail.getUserEmail(),goalUserEmail.getGoalType(),goalUserEmail.getGoalDTO());
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully updated");//[TODO]:edit code to update user
                responseDTO.setContent(goalUserEmail.getGoalDTO());
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Goal Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/deleteGoal")
    public ResponseEntity<ResponseDTO> deleteGoal(@RequestBody GoalDelete goalDelete) {
        try {
            String res = userService.deleteGoal(goalDelete.getUseremail(),goalDelete.getGoalType());
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully deleted");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Goal Not Found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
