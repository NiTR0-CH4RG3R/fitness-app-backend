package com.devgrp.fitnesswebapp.service;

import com.devgrp.fitnesswebapp.dto.ExerciseGetDTO;
import com.devgrp.fitnesswebapp.dto.WorkoutPlanDTO;
import com.devgrp.fitnesswebapp.dto.WorkoutPlanGetDTO;
import com.devgrp.fitnesswebapp.entity.WorkoutPlan;
import com.devgrp.fitnesswebapp.repository.WorkoutPlanRepository;
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
    public List<WorkoutPlanGetDTO> getPublicWorkoutPlans(){
        try{
            List<WorkoutPlan> workoutPlanList=workoutPlanRepository.findByIsPublic(true).orElse(null);
            return modelMapper.map(workoutPlanList,new TypeToken<ArrayList<WorkoutPlanGetDTO>>(){}.getType());
        }
        catch (Exception ex){
            return null;
        }
    }
}
