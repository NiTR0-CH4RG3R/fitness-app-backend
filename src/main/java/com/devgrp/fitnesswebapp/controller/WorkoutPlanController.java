package com.devgrp.fitnesswebapp.controller;


import com.devgrp.fitnesswebapp.dto.ResponseDTO;
import com.devgrp.fitnesswebapp.dto.WorkoutPlanGetDTO;
import com.devgrp.fitnesswebapp.service.WorkoutPlanService;
import com.devgrp.fitnesswebapp.util.EmailReview;
import com.devgrp.fitnesswebapp.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/workoutPlan")
public class WorkoutPlanController {
    @Autowired
    private WorkoutPlanService workoutPlanService;
    @Autowired
    private ResponseDTO responseDTO;
    @GetMapping(value = "get")
    public ResponseEntity<ResponseDTO> getPublicWorkoutPlan(){
        try {
            List<WorkoutPlanGetDTO> workoutPlanGetDTOList = workoutPlanService.getPublicWorkoutPlans();
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
    @PostMapping(value = "/addWorkoutPlanReview")
    public ResponseEntity<ResponseDTO> addWorkoutPlanReview(@RequestBody EmailReview emailReview) {
        try {
            String res = workoutPlanService.addWorkoutPlanReview(emailReview.getUserEmail(),emailReview.getUserReviewDTO());
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(emailReview.getUserReviewDTO());
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }
            else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
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
