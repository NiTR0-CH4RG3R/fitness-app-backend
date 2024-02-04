package com.devgrp.fitnesswebapp.controller;

import com.devgrp.fitnesswebapp.dto.*;
import com.devgrp.fitnesswebapp.entity.types.GoalType;
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
    @PostMapping(value = "/add")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO){
        try{
            String res=userService.createUser(userDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully saved");
                responseDTO.setContent(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }
            /*else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Allready added");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
             */
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
    @PutMapping(value="/update/{userid}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable int userid,@RequestBody UserDTO userDTO){
        try{
            String res=userService.updateUser(userid,userDTO);
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
    @GetMapping(value = "/search/{userId}")
    public ResponseEntity<ResponseDTO> searchUser(@PathVariable int userId){
        UserGetDTO userGetDTO=userService.searchUser(userId);
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
    @DeleteMapping(value = "/delete/{userId}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable int userId){
        try{
            String res=userService.deleteUser(userId);
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
    @PostMapping(value = "/addGoal/{userId}")
    public ResponseEntity<ResponseDTO> addGoal(@PathVariable int userId,@RequestBody GoalDTO goalDTO){
        try{
            String res= userService.addGoal(userId,goalDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Goal Successfully added");
                responseDTO.setContent(goalDTO);
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
    @GetMapping(value = "/getAllGoals/{userId}")
    public ResponseEntity<ResponseDTO> getAllGoals(@PathVariable int userId) {
        try {
            List<GoalGetDTO> goalGetDTOList = userService.getAllGoals(userId);
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
    @PutMapping(value = "/updateGoal/{userID}/{goalType}")
    public ResponseEntity<ResponseDTO> updateGoal(@PathVariable int userID, @PathVariable GoalType goalType,GoalDTO goalDTO){
        try{
            String res=userService.updateGoal(userID,goalType,goalDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully updated");//[TODO]:edit code to update user
                responseDTO.setContent(goalDTO);
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
    @DeleteMapping(value = "/deleteGoal/{userId}/{goalType}")
    public ResponseEntity<ResponseDTO> deleteGoal(@PathVariable int userId,@PathVariable GoalType goalType) {
        try {
            String res = userService.deleteGoal(userId,goalType);
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


    @PostMapping(value = "/updateCreatedWorkoutPlan")
    public ResponseEntity<ResponseDTO> updateFollowedWorkoutPlan(@RequestBody UpdateFollowedWorkoutPlan updateFollowedWorkoutPlan){
        try{
            String res=userService.updateFollowedWorkoutPlan(updateFollowedWorkoutPlan.getUserEmail(),updateFollowedWorkoutPlan.getWorkoutPlanDTO());
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully updated");
                responseDTO.setContent(updateFollowedWorkoutPlan.getWorkoutPlanDTO());
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


    @PostMapping(value = "/updateFollowedWorkoutPlan")
    public ResponseEntity<ResponseDTO> updateCreatedWorkoutPlan(@RequestBody UpdateCreatedWorkoutPlan updateCreatedWorkoutPlan){
        try{
            String res=userService.updateCreatedWorkoutPlan(updateCreatedWorkoutPlan.getUserEmail(),updateCreatedWorkoutPlan.getWorkoutPlanId(),updateCreatedWorkoutPlan.getWorkoutPlanDTO());
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully updated");
                responseDTO.setContent(updateCreatedWorkoutPlan.getWorkoutPlanDTO());
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

    @DeleteMapping(value = "/deleteFollowedWorkoutPlan/{userEmail}")
    public ResponseEntity<ResponseDTO> deleteFollowedWorkoutPlan(@PathVariable String userEmail) {
        try {
            String res = userService.deleteFollowedWorkoutPlan(userEmail);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully deleted");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("WorkoutPlan Not Found");
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
    @DeleteMapping(value = "/deleteCreatededWorkoutPlan")
    public ResponseEntity<ResponseDTO> deleteCreatedWorkoutPlan(@RequestBody UserEmailWorkoutPlanId userEmailWorkoutPlanId) {
        try {
            String res = userService.deleteCreatedWorkoutPlan(userEmailWorkoutPlanId.getUserEmail(),userEmailWorkoutPlanId.getWorkoutPlanId());
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

    //Notification
    @GetMapping(value = "/getAllNotifications/{userEmail}")
    public ResponseEntity<ResponseDTO> getAllNotifications(@PathVariable String userEmail) {
        try {
            List<NotificationGetDTO> notificationGetDTOList = userService.getAllNotifications(userEmail);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("success");
            responseDTO.setContent(notificationGetDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //issues
    @PostMapping(value = "/addIssue")
    public ResponseEntity<ResponseDTO> addIssue(@RequestBody EmailIssue emailIssue){
        try{
            String res= userService.addIssues(emailIssue.getUserEmail(),emailIssue.getIssueDTO());
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Goal Successfully added");
                responseDTO.setContent(emailIssue);
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
    @GetMapping(value = "/getAllIssues/{userEmail}")
    public ResponseEntity<ResponseDTO> getAllIssues(@PathVariable String userEmail) {
        try {
            List<IssueGetDTO> issueGetDTOList = userService.getAllIssues(userEmail);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("success");
            responseDTO.setContent(issueGetDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/updateIssue")
    public ResponseEntity<ResponseDTO> updateIssue(@RequestBody UpdateEmailIssue updateEmailIssue){
        try{
            String res=userService.updateIssue(updateEmailIssue.getUserEmail(),updateEmailIssue.getType(),updateEmailIssue.getIssueDTO());
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully updated");//[TODO]:edit code to update user
                responseDTO.setContent(updateEmailIssue.getIssueDTO());
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Issue Not Found");
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
    @DeleteMapping(value = "/deleteIssue")
    public ResponseEntity<ResponseDTO> deleteIssue(@RequestBody IssueDelete issueDelete) {
        try {
            String res = userService.deleteIssue(issueDelete.getUseremail(),issueDelete.getIssueType());
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully deleted");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("issue Not Found");
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
