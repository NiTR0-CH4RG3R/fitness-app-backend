package com.devgrp.fitnesswebapp.service;
import com.devgrp.fitnesswebapp.dto.ExerciseDTO;
import com.devgrp.fitnesswebapp.dto.ExerciseGetDTO;
import com.devgrp.fitnesswebapp.entity.Exercise;
import com.devgrp.fitnesswebapp.repository.ExerciseRepository;
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
    public ExerciseGetDTO searchExercise(String exerciseName){
        if(exerciseRepository.existsByName(exerciseName)){
            Exercise exercise=exerciseRepository.findByName(exerciseName).orElse(null);
            return modelMapper.map(exercise,ExerciseGetDTO.class);
        }
        else {
            return null;
        }
    }
}
