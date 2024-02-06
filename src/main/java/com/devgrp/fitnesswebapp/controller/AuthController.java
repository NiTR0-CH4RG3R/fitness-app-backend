package com.devgrp.fitnesswebapp.controller;

import com.devgrp.fitnesswebapp.dto.AuthenticationAcceptedDTO;
import com.devgrp.fitnesswebapp.dto.AuthenticationDTO;
import com.devgrp.fitnesswebapp.dto.ResponseDTO;
import com.devgrp.fitnesswebapp.service.AuthenticationService;
import com.devgrp.fitnesswebapp.util.VarList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping(value = "ap1/v1/auth")
public class AuthController {


    private final AuthenticationService service;

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDTO> authenticate(@RequestBody AuthenticationDTO dto) {
        ResponseDTO result = new ResponseDTO();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {
            AuthenticationAcceptedDTO acceptedDTO = service.authenticate(dto).orElse(null);
            if (acceptedDTO == null) {
                result.setCode(VarList.RSP_NOT_AUTHRORIZED);
                result.setMessage("Something went wrong!");
                result.setContent(null);
            }
            else {
                result.setCode(VarList.RSP_SUCCESS);
                result.setMessage("Successful!");
                result.setContent(acceptedDTO);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception ex) {
            result.setCode(VarList.RSP_ERROR);
            result.setMessage(ex.getMessage());
            result.setContent(null);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<ResponseDTO>(result, status);
    }

}
