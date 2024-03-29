package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.entity.types.NotificationType;
import jakarta.persistence.*;
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
    private Integer id;

    @Column( nullable = false )
    private NotificationType type = NotificationType.OTHER;

    @Column( nullable = false )
    private String message;

    @Column( nullable = false )
    private LocalDateTime createdDateTime;

    @ManyToOne
    @JoinColumn( nullable = false )
    private User receiver;

    private LocalDateTime receivedDateTime;

    @ManyToOne
    @JoinColumn
    private Goal goal;

    @ManyToOne
    @JoinColumn
    private WorkoutPlan workoutPlan;
}
