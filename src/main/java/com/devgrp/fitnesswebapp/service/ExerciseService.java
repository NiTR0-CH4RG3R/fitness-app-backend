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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
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
        System.out.println(noOfElements);
        List<Exercise> exerciseList=exerciseRepository.findAll(PageRequest.of(pageNo,noOfElements)).toList();
        System.out.println(exerciseList);
        List<ExerciseGetDTO> results = new ArrayList<>();

        for (var exercise :
                exerciseList) {
            ExerciseGetDTO e = new ExerciseGetDTO();
            e.setDescription(exercise.getDescription());
            e.setId(exercise.getId());
            e.setName(exercise.getName());
            e.setGifURL(exercise.getGifURL());
            results.add(e);
        }
        return results;
    }
    public long getExerciseCount(){
        return exerciseRepository.count();
    }
    public String updateExercise(Integer id, ExerciseDTO data){
        try {
            Exercise e = modelMapper.map(data, Exercise.class);
            e.setId(id);
            exerciseRepository.save(e);
            return VarList.RSP_SUCCESS;
        }
        catch ( Exception e ) {
            System.out.println(e.getMessage());
            return VarList.RSP_ERROR;
        }
    }
    public String deleteExercise(Integer id){
        try {
            exerciseRepository.deleteById(id);
        }
        catch (Exception e ) {
            System.out.println(e.getMessage());
            return VarList.RSP_ERROR;
        }
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
    public List<UserReviewDTO> getExerciseUserReview( Integer exerciseId ){
        try{
            var exericse = exerciseRepository.findById(exerciseId).orElse(null);
            var reviews = exericse.getUserReviews();
            ArrayList<UserReviewDTO> result = new ArrayList<>();
            for (var review :
                    reviews) {
                var r = new UserReviewDTO();
                r.setEntityId(review.getId().getUserId());
                r.setRating(review.getRating());
                r.setComment(review.getComment());
                result.add(r);
            }

            return result;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
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
