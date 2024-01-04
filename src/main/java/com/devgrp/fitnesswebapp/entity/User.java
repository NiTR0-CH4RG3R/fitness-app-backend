package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
enum UserType{
    ADMIN,USER;
}
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="User")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private UserType type;
    private String email;
    private LocalDate dob;
    private String address;
    private String telphoneNo;
    private float weight;
    private String bloodGroup;
    private String healthIsuues;
    private String emrgencyContact;
    private WorkoutPlan followingWorkoutPlan;
    private boolean goalNotifcation;
    private boolean workoutPlanNotifcation;
    private boolean otherNotifcation;

}
