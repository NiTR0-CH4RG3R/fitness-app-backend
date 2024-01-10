package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.GoalDailyProgress;
import com.devgrp.fitnesswebapp.entity.compositekeys.GoalDailyProgressKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalDailyProgressRepository extends JpaRepository<GoalDailyProgress, GoalDailyProgressKey> {}
