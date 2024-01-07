package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.UserExerciseLog;
import com.devgrp.fitnesswebapp.entity.compositekeys.UserExerciseLogKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExerciseLogRepository extends JpaRepository<UserExerciseLog, UserExerciseLogKey> {}
