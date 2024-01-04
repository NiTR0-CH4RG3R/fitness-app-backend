package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="UserLoggingLog")
@AllArgsConstructor
@NoArgsConstructor
public class UserLoggingLog {
    @Id
    private String userID;
    private LocalDateTime loggedInDate;
    private LocalDateTime loggedOutDate;
}
