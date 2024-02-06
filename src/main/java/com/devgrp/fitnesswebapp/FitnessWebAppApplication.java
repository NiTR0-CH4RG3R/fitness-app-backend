package com.devgrp.fitnesswebapp;

import com.devgrp.fitnesswebapp.entity.Exercise;
import com.devgrp.fitnesswebapp.entity.Goal;
import com.devgrp.fitnesswebapp.entity.User;
import com.devgrp.fitnesswebapp.entity.types.GoalType;
import com.devgrp.fitnesswebapp.entity.types.UserType;
import com.devgrp.fitnesswebapp.repository.ExerciseRepository;
import com.devgrp.fitnesswebapp.repository.UserRepository;
import com.devgrp.fitnesswebapp.repository.WorkoutExerciseRepository;
import com.devgrp.fitnesswebapp.repository.WorkoutPlanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FitnessWebAppApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(FitnessWebAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("*");
			}
		};
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
//
//	@Autowired ExerciseRepository exerciseRepository;
//
//	@Autowired WorkoutPlanRepository workoutPlanRepository;
//
//	@Autowired UserRepository userRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setFirstName( "Buddhima" );
//		user.setLastName( "Zoysa" );
//		user.setType( UserType.USER );
//		user.setEmail("buddhimakaveeshwara@gmail.com");
//		user.setPassword("ABCD");
//		user.setDob(LocalDate.now());
//		user.setAddress("Homeless");
//		user.setTelephoneNo("No Contact");
//		user.setEmergencyContact("NONO");
//
//		var goals = new ArrayList<Goal>();
//		var goal = new Goal();
//		goal.setType(GoalType.WEIGHT_LOSS);
//		goal.setAchievementValue(8);
//		goal.setFollowedBy(user);
//		goals.add(goal);
//
//		user.setGoals(goals);
//
//		userRepository.save(user);
//
//	}
}
