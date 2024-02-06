package com.devgrp.fitnesswebapp.service;

import com.devgrp.fitnesswebapp.dto.*;
import com.devgrp.fitnesswebapp.entity.*;
import com.devgrp.fitnesswebapp.entity.compositekeys.GoalDailyProgressKey;
import com.devgrp.fitnesswebapp.entity.types.GoalType;
import com.devgrp.fitnesswebapp.entity.types.IssueType;
import com.devgrp.fitnesswebapp.repository.GoalRepository;
import com.devgrp.fitnesswebapp.repository.NotificationRepository;
import com.devgrp.fitnesswebapp.repository.UserRepository;
import com.devgrp.fitnesswebapp.repository.WorkoutPlanRepository;
import com.devgrp.fitnesswebapp.util.VarList;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    @Autowired
    private NotificationRepository notificationRepository;

    public String createUser(UserDTO userDTO) {
        try{
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }
        catch (Exception ex){
            return ex.getMessage();
        }
        /*if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.RSP_DUPLICATED;
        } else {
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }

         */
    }

    public String updateUser(int userId,UserDTO userUpdateDTO) {
        try{
            User user=modelMapper.map(userUpdateDTO, User.class);
            user.setId(userId);
            userRepository.save(user);
            return VarList.RSP_SUCCESS;
        }
        catch (Exception e){
                return VarList.RSP_ERROR;
        }
    }

    public List<UserGetDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return modelMapper.map(userList, new TypeToken<ArrayList<UserGetDTO>>() {
        }.getType());
    }

    public UserGetDTO searchUser(int userId) {
        try {
            User user = userRepository.findById(userId).orElse(null);
            return modelMapper.map(user, UserGetDTO.class);
        }
        catch (Exception ex){
            return null;
        }
    }

    public String deleteUser(int userId) {
        try{
            userRepository.deleteById(userId);
            return VarList.RSP_SUCCESS;
        } catch (Exception ex){
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public String addGoal(int userId, GoalDTO goalDTO) {
        try {
            User user = userRepository.findById(userId).orElse(null);
            if(user==null) return VarList.RSP_ERROR;
            Goal goal = modelMapper.map(goalDTO, Goal.class);
            if (user.getGoals() == null) {
                ArrayList<Goal> goals = new ArrayList<>();
                goals.add(goal);
            } else {
                user.getGoals().add(user.getGoals().size(), goal);
            }
            goal.setFollowedBy(user);
            goalRepository.save(goal);
            //userRepository.save(user);
            return VarList.RSP_SUCCESS;

        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public List<GoalGetDTO> getAllGoals(int userId) {
        User user = userRepository.findById(userId).orElse(null);
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
    public String updateGoal(Integer id, GoalDTO goalDTO){
        try {
            Goal g = modelMapper.map(goalDTO, Goal.class);
            g.setId( id );
            goalRepository.save(g);
        }
        catch ( Exception e ) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return VarList.RSP_SUCCESS;
    }

    public String deleteGoal(int userId, Integer id){
        try {
            goalRepository.deleteById(id);
        }
        catch ( Exception e ) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return VarList.RSP_SUCCESS;
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
            workoutPlanList.set(j, modelMapper.map(workoutPlanDTO, WorkoutPlan.class));//[Todo]:Complete this
            user.setCreatedWorkoutPlans(workoutPlanList);
            userRepository.save(user);
            return VarList.RSP_SUCCESS;
        }
        return VarList.RSP_NO_DATA_FOUND;
    }
    public String deleteFollowedWorkoutPlan(String userEmail){
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return VarList.RSP_NO_DATA_FOUND;
        if (user.getFollowingWorkoutPlan()==null) return VarList.RSP_NO_DATA_FOUND;
        user.setFollowingWorkoutPlan(null);
        userRepository.save(user);
        return VarList.RSP_SUCCESS;
    }

    public String deleteCreatedWorkoutPlan(String userEmail,int workoutPlanId){
        User user=userRepository.findUserByEmail(userEmail).orElse(null);
        if(user==null) return VarList.RSP_NO_DATA_FOUND;
        List<WorkoutPlan> workoutPlanList=user.getCreatedWorkoutPlans();
        if(workoutPlanList==null) return VarList.RSP_NO_DATA_FOUND;
        int j = 0;
        while (workoutPlanList.get(j).getId() != workoutPlanId){
            j++;
        }
        if(j < workoutPlanList.size()-1){
            workoutPlanList.remove(j);
            user.setCreatedWorkoutPlans(workoutPlanList);
            userRepository.save(user);
            return VarList.RSP_SUCCESS;
        }
        return VarList.RSP_NO_DATA_FOUND;

    }
    //Notification
    public List<NotificationGetDTO> getAllNotifications(String userEmail){
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return null;
        List<Notification> notificationList = user.getNotifications();
        if (notificationList == null) return null;
        return modelMapper.map(notificationList, new TypeToken<ArrayList<NotificationGetDTO>>() {
        }.getType());
    }
    //issues
    public String addIssues(String userEmail,IssueDTO issueDTO){
        try {
            User user=userRepository.findUserByEmail(userEmail).orElse(null);
            if(user==null) return VarList.RSP_NO_DATA_FOUND;
            Issue issue = modelMapper.map(issueDTO, Issue.class);
            if (user.getIssues() == null) {
                ArrayList<Issue> issues = new ArrayList<>();
                issues.add(issue);
            } else {
                user.getIssues().add(user.getIssues().size(), issue);
            }
            userRepository.save(user);
            return VarList.RSP_SUCCESS;

        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public List<IssueGetDTO> getAllIssues(String userEmail) {
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return null;
        List<Issue> issueList = user.getIssues();
        if (issueList == null) return null;
        return modelMapper.map(issueList, new TypeToken<ArrayList<GoalGetDTO>>() {
        }.getType());
    }
    public String updateIssue(String userEmail, IssueType issueType, IssueDTO issueDTO){
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return VarList.RSP_NO_DATA_FOUND;
        List<Issue> issueList = user.getIssues();
        if (issueList == null) return VarList.RSP_NO_DATA_FOUND;
        int j=0;
        while (issueList.get(j).getType() != issueType) {
            j++;
        }
        if (j < issueList.size() - 1) {
            issueList.set(j, modelMapper.map(issueDTO, Issue.class));
            user.setIssues(issueList);
            userRepository.save(user);
            return VarList.RSP_SUCCESS;
        }
        return VarList.RSP_NO_DATA_FOUND;
    }
    public String deleteIssue(String userEmail, IssueType issueType){
        User user = userRepository.findUserByEmail(userEmail).orElse(null);
        if (user == null) return VarList.RSP_NO_DATA_FOUND;
        List<Issue> issueList = user.getIssues();
        if (issueList == null) return VarList.RSP_NO_DATA_FOUND;
        int j = 0;
        while (issueList.get(j).getType() != issueType){
            j++;
        }
        if(j < issueList.size()-1){
            issueList.remove(j);
            user.setIssues(issueList);
            userRepository.save(user);
            return VarList.RSP_SUCCESS;
        }
        return VarList.RSP_NO_DATA_FOUND;
    }
    public String addDailyProgress(Integer userId, Integer id, GoalDTO data){
        try{


        }
        catch (Exception ex){
           return VarList.RSP_ERROR;
        }

        return VarList.RSP_NO_DATA_FOUND;
    }
    public List<GoalDailyProgressDTO> getGoalDailyProgress( int goalId){
        try {
            var goal = goalRepository.findById(goalId).orElse(null);
            var progresses = goal.getDailyProgress();
            var progressDTOs = new ArrayList<GoalDailyProgressDTO>();

            for ( var progress : progresses ) {
                progressDTOs.add(modelMapper.map(progress, GoalDailyProgressDTO.class));
            }

            return progressDTOs;
        }
        catch ( Exception e ) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String deleteGoalDailyProgress(String userEmail, int goalId, GoalDailyProgressKey goalDailyProgressKey){
        User user=userRepository.findUserByEmail(userEmail).orElse(null);
        if (user==null) return VarList.RSP_NO_DATA_FOUND;
        List<Goal> goalList=user.getGoals();
        if(goalList==null) return VarList.RSP_NO_DATA_FOUND;
        int j=0;
        while (goalList.get(j).getId() != goalId){
            j++;
        }
        List<GoalDailyProgress> goalDailyProgressList=goalList.get(j).getDailyProgress();
        if(j < goalList.size()-1) {
            if (goalList.get(j).getDailyProgress() == null) ;
            return VarList.RSP_NO_DATA_FOUND;
        }
        else{
            int k=0;
            while (goalDailyProgressList.get(k).getId() != goalDailyProgressKey){
                k++;
            }
            goalDailyProgressList.remove(k);
            goalList.get(j).setDailyProgress(goalDailyProgressList);
            user.setGoals(goalList);
            userRepository.save(user);
            return VarList.RSP_SUCCESS;
        }
    }

}