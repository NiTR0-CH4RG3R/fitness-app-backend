package com.devgrp.fitnesswebapp.dto;

import com.devgrp.fitnesswebapp.entity.types.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationAcceptedDTO {
    Integer user;
    String accessToken;
    UserType[] roles;
}
