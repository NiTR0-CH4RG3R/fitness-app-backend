package com.devgrp.fitnesswebapp.service;

import com.devgrp.fitnesswebapp.dto.ExerciseGetDTO;
import com.devgrp.fitnesswebapp.dto.UserReviewDTO;
import com.devgrp.fitnesswebapp.dto.WorkoutPlanDTO;
import com.devgrp.fitnesswebapp.dto.WorkoutPlanGetDTO;
import com.devgrp.fitnesswebapp.entity.User;
import com.devgrp.fitnesswebapp.entity.WorkoutPlan;
import com.devgrp.fitnesswebapp.entity.WorkoutPlanUserReview;
import com.devgrp.fitnesswebapp.repository.UserRepository;
import com.devgrp.fitnesswebapp.repository.WorkoutPlanRepository;
import com.devgrp.fitnesswebapp.repository.WorkoutPlanUserReviewRepository;
import com.devgrp.fitnesswebapp.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WorkoutPlanService {
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;    @Autowired
    private WorkoutPlanUserReviewRepository workoutPlanUserReviewRepository;
    public List<WorkoutPlanGetDTO> getPublicWorkoutPlans(){
        try{
            List<WorkoutPlan> workoutPlanList=workoutPlanRepository.findByIsPublic(true).orElse(null);
            return modelMapper.map(workoutPlanList,new TypeToken<ArrayList<WorkoutPlanGetDTO>>(){}.getType());
        }
        catch (Exception ex){
            return null;
        }
    }
    public String addWorkoutPlanReview(String userEmail, UserReviewDTO userReviewDTO){
        try{
            User user=userRepository.findUserByEmail(userEmail).orElse(null);
            if(user==null) return VarList.RSP_NO_DATA_FOUND;
            WorkoutPlan workoutPlan=workoutPlanRepository.findById(userReviewDTO.getEntityId()).orElse(null);
            if (workoutPlan==null) return VarList.RSP_NO_DATA_FOUND;
            WorkoutPlanUserReview workoutPlanUserReview=new WorkoutPlanUserReview();
            workoutPlanUserReview.setUser(user);
            workoutPlanUserReview.setWorkoutPlan(workoutPlan);
            workoutPlanUserReview.setComment(userReviewDTO.getComment());
            workoutPlanUserReview.setRating(userReviewDTO.getRating());
            workoutPlanUserReviewRepository.save(workoutPlanUserReview);
            return VarList.RSP_SUCCESS;
        }
        catch (Exception ex){
            return VarList.RSP_ERROR;
        }
    }
}
