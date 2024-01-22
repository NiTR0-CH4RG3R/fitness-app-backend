package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.Exercise;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ExercisePageRepository extends PagingAndSortingRepository<Exercise,Integer> {
    List<Exercise> findAll(Pageable page);
}
