package com.devgrp.fitnesswebapp.service;

import com.devgrp.fitnesswebapp.dto.ExerciseDTO;
import com.devgrp.fitnesswebapp.dto.ExerciseGetDTO;
import com.devgrp.fitnesswebapp.dto.UserDTO;
import com.devgrp.fitnesswebapp.entity.Exercise;
import com.devgrp.fitnesswebapp.entity.User;
import com.devgrp.fitnesswebapp.repository.ExerciseRepository;
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
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private ModelMapper modelMapper;
    public String addExercise(ExerciseDTO exerciseDTO) {
        if (exerciseRepository.existsById(exerciseDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }
        else{
            exerciseRepository.save(modelMapper.map(exerciseDTO, Exercise.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public List<ExerciseGetDTO> getExercise(int offset,int count){
        ArrayList<Exercise> exerciseList=new ArrayList<Exercise>(count);
        for(int i=offset;i<=offset+count;i++) {
           // exerciseList[i]=exerciseRepository.findById(i).orElse(null);
            exerciseList.add(exerciseList.size(), exerciseRepository.findById(i).orElse(null));//[TODO]:find the best way to get data from db
        }
        return modelMapper.map(exerciseList,new TypeToken<ArrayList<ExerciseGetDTO>>(){}.getType());
    }
    public long getExerciseCount(){
        return exerciseRepository.count();
    }
    public String updateExercise(ExerciseDTO exerciseDTO){
        if(exerciseRepository.existsById(exerciseDTO.getId())){
            exerciseRepository.save(modelMapper.map(exerciseDTO,Exercise.class));
            return VarList.RSP_SUCCESS;
        }
        else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public ExerciseGetDTO searchExercise(int exerciseID){
        if(exerciseRepository.existsById(exerciseID)){
            Exercise exercise=exerciseRepository.findById(exerciseID).orElse(null);
            return modelMapper.map(exercise,ExerciseGetDTO.class);
        }
        else {
            return null;
        }
    }
}
