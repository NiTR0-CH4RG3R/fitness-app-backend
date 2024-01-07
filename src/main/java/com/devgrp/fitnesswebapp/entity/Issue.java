package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.entity.types.IssueType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue
    private Integer id;

    @Column( nullable = false )
    private IssueType type = IssueType.OTHER;

    @ManyToOne
    @JoinColumn
    private Goal goal;

    @ManyToOne
    @JoinColumn
    private WorkoutPlan workoutPlan;

    @ManyToOne
    @JoinColumn( nullable = false)
    private User createdBy;

    @Column( nullable = false )
    private LocalDateTime createdDateTime;
}
