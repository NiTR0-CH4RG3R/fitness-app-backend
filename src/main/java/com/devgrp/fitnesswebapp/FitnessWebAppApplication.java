package com.devgrp.fitnesswebapp;

import com.devgrp.fitnesswebapp.entity.Exercise;
import com.devgrp.fitnesswebapp.repository.ExerciseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FitnessWebAppApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(FitnessWebAppApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

//	@Autowired
//	ExerciseRepository exerciseRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Exercise e = new Exercise();
//		e.setName("New Exercise");
//		e.setDescription("Some Description");
//		e.setTargetMuscle(new ArrayList<>());
//		e.getTargetMuscle().add("SomeTargetMuscle");
//		exerciseRepository.save(e);
//	}
}
