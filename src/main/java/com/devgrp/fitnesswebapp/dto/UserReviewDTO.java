package com.devgrp.fitnesswebapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReviewDTO {
    private Integer entityId;
    private Integer rating;
    private String comment;
}
