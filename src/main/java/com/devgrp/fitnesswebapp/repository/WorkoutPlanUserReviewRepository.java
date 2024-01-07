package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.WorkoutPlanUserReview;
import com.devgrp.fitnesswebapp.entity.compositekeys.WorkoutPlanUserReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPlanUserReviewRepository extends JpaRepository<WorkoutPlanUserReview, WorkoutPlanUserReviewKey> {}
