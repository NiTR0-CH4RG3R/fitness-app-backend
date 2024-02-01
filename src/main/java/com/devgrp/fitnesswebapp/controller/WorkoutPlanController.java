package com.devgrp.fitnesswebapp.controller;

import com.devgrp.fitnesswebapp.dto.ExerciseGetDTO;
import com.devgrp.fitnesswebapp.dto.ResponseDTO;
import com.devgrp.fitnesswebapp.dto.WorkoutPlanGetDTO;
import com.devgrp.fitnesswebapp.service.WorkoutPlanService;
import com.devgrp.fitnesswebapp.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
