package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="UserExcerciseLog")
public class UserExcerciseLog {
    @Id
    public String userID;
    @Id
    public String exerciseID;
    private int repCount;
    private int setCount;
    private LocalDateTime dateTime;
}
