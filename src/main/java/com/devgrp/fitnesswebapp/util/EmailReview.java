package com.devgrp.fitnesswebapp.util;

import com.devgrp.fitnesswebapp.dto.UserReviewDTO;
import lombok.Data;

@Data
public class EmailReview {
    private String userEmail;
    private UserReviewDTO userReviewDTO;
}
