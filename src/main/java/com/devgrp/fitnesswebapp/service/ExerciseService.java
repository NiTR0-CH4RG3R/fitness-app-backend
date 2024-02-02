package com.devgrp.fitnesswebapp.service;
import com.devgrp.fitnesswebapp.dto.ExerciseDTO;
import com.devgrp.fitnesswebapp.dto.ExerciseGetDTO;
import com.devgrp.fitnesswebapp.dto.UserReviewDTO;
import com.devgrp.fitnesswebapp.dto.UserReviewGetDTO;
import com.devgrp.fitnesswebapp.entity.*;
import com.devgrp.fitnesswebapp.entity.compositekeys.ExerciseUserReviewKey;
import com.devgrp.fitnesswebapp.repository.*;
import com.devgrp.fitnesswebapp.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;
    @Autowired
    private ExerciseUserReviewRepository exerciseUserReviewRepository;

    public String addExercise(ExerciseDTO exerciseDTO) {
        if (exerciseRepository.existsByName(exerciseDTO.getName())){
            return VarList.RSP_DUPLICATED;
        }
        else{
            exerciseRepository.save(modelMapper.map(exerciseDTO, Exercise.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public List<ExerciseGetDTO> getExercise(int pageNo,int noOfElements){
        List<Exercise> exerciseList=exerciseRepository.findAll(PageRequest.of(pageNo,noOfElements)).toList();
        return modelMapper.map(exerciseList,new TypeToken<ArrayList<ExerciseGetDTO>>(){}.getType());
    }
    public long getExerciseCount(){
        return exerciseRepository.count();
    }
    public String updateExercise(ExerciseGetDTO exerciseGetDTO){
        if(exerciseRepository.existsByName(exerciseGetDTO.getName())){
            exerciseRepository.save(modelMapper.map(exerciseGetDTO,Exercise.class));
            return VarList.RSP_SUCCESS;
        }
        else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public String deleteExercise(String exerciseName){
        Exercise exercise=exerciseRepository.findByName(exerciseName).orElse(null);
        if(exercise==null) return VarList.RSP_NO_DATA_FOUND;
        exerciseRepository.delete(exercise);
        return VarList.RSP_SUCCESS;
    }
    public ExerciseGetDTO searchExercise(String exerciseName){
        if(exerciseRepository.existsByName(exerciseName)){
            Exercise exercise=exerciseRepository.findByName(exerciseName).orElse(null);
            return modelMapper.map(exercise,ExerciseGetDTO.class);
        }
        else {
            return null;
        }
    }
    public String addExerciseReview(String userEmail,UserReviewDTO userReviewDTO){
        try{
            User user=userRepository.findUserByEmail(userEmail).orElse(null);
            if(user==null) return VarList.RSP_NO_DATA_FOUND;
            Exercise exercise=exerciseRepository.findById(userReviewDTO.getEntityId()).orElse(null);
            if (exercise==null) return VarList.RSP_NO_DATA_FOUND;
            ExerciseUserReview exerciseUserReview=new ExerciseUserReview();
            exerciseUserReview.setUser(user);
            exerciseUserReview.setExercise(exercise);
            exerciseUserReview.setComment(userReviewDTO.getComment());
            exerciseUserReview.setRating(userReviewDTO.getRating());
            exerciseUserReviewRepository.save(exerciseUserReview);
            return VarList.RSP_SUCCESS;
        }
        catch (Exception ex){
            return VarList.RSP_ERROR;
        }
    }
    public List<UserReviewGetDTO> getExerciseUserReview(){
        try{
            List<ExerciseUserReview> exerciseUserReviewList=exerciseUserReviewRepository.findAll();
            return modelMapper.map(exerciseUserReviewList,new TypeToken<ArrayList<UserReviewDTO>>(){}.getType());
        }
        catch (Exception ex){
            return null;
        }
    }
    public String deleteExerciseReview(ExerciseUserReviewKey exerciseReviewId){
        ExerciseUserReview exerciseUserReview=exerciseUserReviewRepository.findById(exerciseReviewId).orElse(null);
        if(exerciseUserReview==null) return VarList.RSP_NO_DATA_FOUND;
        exerciseUserReviewRepository.delete(exerciseUserReview);
        return VarList.RSP_SUCCESS;
    }
}
