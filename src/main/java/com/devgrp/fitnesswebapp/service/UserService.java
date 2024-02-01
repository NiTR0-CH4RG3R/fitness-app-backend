package com.devgrp.fitnesswebapp.service;

import com.devgrp.fitnesswebapp.dto.*;
import com.devgrp.fitnesswebapp.entity.Goal;
import com.devgrp.fitnesswebapp.entity.User;
import com.devgrp.fitnesswebapp.entity.WorkoutPlan;
import com.devgrp.fitnesswebapp.entity.types.GoalType;
import com.devgrp.fitnesswebapp.repository.GoalRepository;
import com.devgrp.fitnesswebapp.repository.UserRepository;
import com.devgrp.fitnesswebapp.repository.WorkoutPlanRepository;
import com.devgrp.fitnesswebapp.util.VarList;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GoalRepository goalRepository;

    public String createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.RSP_DUPLICATED;
        } else {
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<UserGetDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return modelMapper.map(userList, new TypeToken<ArrayList<UserGetDTO>>() {
        }.getType());
    }

    public UserGetDTO searchUser(String userEmail) {
        if (userRepository.existsByEmail(userEmail)) {
            User user = userRepository.findUserByEmail(userEmail).orElse(null);
            return modelMapper.map(user, UserGetDTO.class);
        } else {
            return null;
        }
    }

    public String deleteUser(String userEmail) {
        if (userRepository.existsByEmail(userEmail)) {
            userRepository.deleteByEmail(userEmail);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public String addGoal(String userEmail, GoalDTO goalDTO) {
        try {
            User user = userRepository.findUserByEmail(userEmail).orElse(null);
            if(user==null) return VarList.RSP_ERROR;
            Goal goal = modelMapper.map(goalDTO, Goal.class);
            if (user.getGoals() == null) {
                ArrayList<Goal> goals = new ArrayList<>();
                goals.add(goal);
            } else {
                user.getGoals().add(user.getGoals().size(), goal);
            }
            userRepository.save(user);
            return VarList.RSP_SUCCESS;
            /*if (userRepository.existsByEmail(userEmail)) {
                User user = userRepository.findUserByEmail(userEmail).orElse(null);
                Goal goal = modelMapper.map(goalDTO, Goal.class);
                assert user != null;
                if (user.getGoals() == null) {
                    ArrayList<Goal> goals = new ArrayList<>();
                    goals.add(goal);
                } else {
                    user.getGoals().add(user.getGoals().size(), goal);
                }
                userRepository.save(user);
                return VarList.RSP_SUCCESS;
            } else {
                return VarList.RSP_ERROR;
            }*/
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public List<GoalGetDTO> getAllGoals(String userEmail) {
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return null;
        List<Goal> goalList = user.getGoals();
        if (goalList == null) return null;
        return modelMapper.map(goalList, new TypeToken<ArrayList<GoalGetDTO>>() {
        }.getType());
    }

    public GoalGetDTO searchGoal(String userEmail, GoalType goalType) {
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return null;
        List<Goal> goalList = user.getGoals();
        if (goalList == null) return null;
        int i = 0;
        while (goalList.get(i).getType() != goalType) {
            i++;
        }
        if (i < goalList.size()-1) {
            return modelMapper.map(goalList.get(i), GoalGetDTO.class);
        }
        return null;
    }
    public String updateGoal(String userEmail, GoalType goalType,GoalDTO goalDTO){
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return VarList.RSP_NO_DATA_FOUND;
        List<Goal> goalList = user.getGoals();
        if (goalList == null) return VarList.RSP_NO_DATA_FOUND;
        int j=0;
        while (goalList.get(j).getType() != goalType) {
            j++;
        }
        if (j < goalList.size() - 1) {
            goalList.set(j, modelMapper.map(goalDTO, Goal.class));
            user.setGoals(goalList);
            userRepository.save(user);
            return VarList.RSP_SUCCESS;
        }
        return VarList.RSP_NO_DATA_FOUND;
    }

    public String deleteGoal(String userEmail, GoalType goalType){
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return VarList.RSP_NO_DATA_FOUND;
        List<Goal> goalList = user.getGoals();
        if (goalList == null) return VarList.RSP_NO_DATA_FOUND;
        int j = 0;
        while (goalList.get(j).getType() != goalType){
            j++;
        }
        if(j < goalList.size()-1){
            goalList.remove(j);
            user.setGoals(goalList);
            userRepository.save(user);
            return VarList.RSP_SUCCESS;
        }
        return VarList.RSP_NO_DATA_FOUND;
    }
//Workout plan
public String addWorkoutPlan(String userEmail, WorkoutPlanDTO workoutPlanDTO) {
    try {
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if(user==null) return VarList.RSP_ERROR;
        WorkoutPlan workoutPlan = modelMapper.map(workoutPlanDTO, WorkoutPlan.class);
        //from goal list
        if (user.getCreatedWorkoutPlans() == null) {
            ArrayList<WorkoutPlan> createdWorkoutPlans = new ArrayList<>();
            createdWorkoutPlans.add(workoutPlan);
        } else {
            user.getCreatedWorkoutPlans().add(user.getCreatedWorkoutPlans().size(), workoutPlan);
        }
        userRepository.save(user);
        return VarList.RSP_SUCCESS;

    } catch (Exception e) {
        return e.getMessage();
    }
}
private WorkoutPlanRepository workoutPlanRepository;
public String followWorkoutPlan(String userEmail,int workoutPlanId){
    try {
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if(user==null) return VarList.RSP_NO_DATA_FOUND;
        WorkoutPlan workoutPlan =workoutPlanRepository.findById(workoutPlanId).orElse(null);
        if(workoutPlan==null) return VarList.RSP_NO_DATA_FOUND;
        user.setFollowingWorkoutPlan(workoutPlan);
        userRepository.save(user);
        return VarList.RSP_SUCCESS;
    } catch (Exception e) {
        return e.getMessage();
    }
}
    public WorkoutPlanGetDTO getFollowedWorkoutPlan(String userEmail) {
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return null;
        if (user.getFollowingWorkoutPlan() == null) return null;
        return modelMapper.map(user.getFollowingWorkoutPlan(),WorkoutPlanGetDTO.class);
    }
    public List<WorkoutPlanGetDTO> getCreatedWorkoutPlans(String userEmail) {
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return null;
        List<WorkoutPlan> workoutPlanList = user.getCreatedWorkoutPlans();
        if (workoutPlanList == null) return null;
        return modelMapper.map(workoutPlanList, new TypeToken<ArrayList<WorkoutPlanGetDTO>>() {
        }.getType());
    }

    public String updateFollowedWorkoutPlan(String userEmail,WorkoutPlanDTO workoutPlanDTO){
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return VarList.RSP_NO_DATA_FOUND;
        if (user.getFollowingWorkoutPlan()==null) return VarList.RSP_NO_DATA_FOUND;
        user.setFollowingWorkoutPlan(modelMapper.map(workoutPlanDTO,WorkoutPlan.class));
        userRepository.save(user);
        return VarList.RSP_SUCCESS;
    }

    public String updateCreatedWorkoutPlan(String userEmail,int workoutPlanId,WorkoutPlanDTO workoutPlanDTO) {
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return VarList.RSP_NO_DATA_FOUND;
        List<WorkoutPlan> workoutPlanList=user.getCreatedWorkoutPlans();
        if (user.getCreatedWorkoutPlans() == null) return VarList.RSP_NO_DATA_FOUND;

        ///
        int j=0;
        while (workoutPlanList.get(j).getId() != workoutPlanId) {
            j++;
        }
        if (j < workoutPlanList.size() - 1) {
            workoutPlanList.set(j, modelMapper.map(goalDTO, Goal.class));//[Todo]:Complete this
            user.setGoals(goalList);
            userRepository.save(user);
            return VarList.RSP_SUCCESS;
        }
        return VarList.RSP_NO_DATA_FOUND;
    }
    public String deleteWorkoutPlan(String userEmail){
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return VarList.RSP_NO_DATA_FOUND;
        if (user.getFollowingWorkoutPlan()==null) return VarList.RSP_NO_DATA_FOUND;
        user.setFollowingWorkoutPlan(null);
        userRepository.save(user);
        return VarList.RSP_SUCCESS;

        /*List<Goal> goalList = user.getGoals();
        if (goalList == null) return VarList.RSP_NO_DATA_FOUND;
        int j = 0;
        while (goalList.get(j).getType() != goalType){
            j++;
        }
        if(j < goalList.size()-1){
            goalList.remove(j);
            user.setGoals(goalList);
            userRepository.save(user);
            return VarList.RSP_SUCCESS;
        }
        return VarList.RSP_NO_DATA_FOUND;*/
    }

}