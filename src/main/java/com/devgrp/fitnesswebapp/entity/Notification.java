package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.common.NotificationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue
    private int id;

    private NotificationType type;

    private String message;

    private LocalDateTime createdDateTime;

    private int receiverID; // TODO : Reference to User

    private LocalDateTime receivedDateTime;

    // TODO : Create foreign keys
    // Also these are optional values.
    private int goalID;
    private int workoutPlanID;


}
