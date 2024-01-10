package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.ExerciseUserReview;
import com.devgrp.fitnesswebapp.entity.compositekeys.ExerciseUserReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseUserReviewRepository extends JpaRepository<ExerciseUserReview, ExerciseUserReviewKey> {}
