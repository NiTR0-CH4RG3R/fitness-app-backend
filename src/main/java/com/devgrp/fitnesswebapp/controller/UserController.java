package com.devgrp.fitnesswebapp.controller;

import com.devgrp.fitnesswebapp.dto.ResponseDTO;
import com.devgrp.fitnesswebapp.dto.UserDTO;
import com.devgrp.fitnesswebapp.service.UserService;
import com.devgrp.fitnesswebapp.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ResponseDTO responseDTO;
    @PatchMapping(value = "add")
    public ResponseEntity addUser(@RequestBody UserDTO userDTO){
        try{
            String res=userService.createUser(userDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Succesfully saved");
                responseDTO.setContent(userDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Allready added");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
            else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
