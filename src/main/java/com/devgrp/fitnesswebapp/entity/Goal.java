package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.common.GoalType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goal {
    @Id
    @GeneratedValue
    private int id;

    @Column( nullable = false )
    private GoalType type;

    @Column( nullable = false )
    private float achievementValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( nullable = false )
    private User followedBy;

    @OneToMany(mappedBy = "goal")
    private List<GoalDailyProgress> dailyProgress;

    @OneToMany(mappedBy = "goal")
    private List<Issue> issues;

    @OneToMany(mappedBy = "goal")
    private List<Notification> notifications;
}
