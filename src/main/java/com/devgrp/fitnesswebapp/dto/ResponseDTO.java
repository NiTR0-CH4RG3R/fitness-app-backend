package com.devgrp.fitnesswebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO {
    private String code;
    private String message;
    private Object content;
}
