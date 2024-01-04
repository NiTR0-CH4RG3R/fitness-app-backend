package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="Exercise")
public class Exercise {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private String equipment;
    private String gifURL;

}
