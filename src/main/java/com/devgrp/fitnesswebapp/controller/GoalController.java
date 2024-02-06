package com.devgrp.fitnesswebapp.controller;

import com.devgrp.fitnesswebapp.dto.GoalDTO;
import com.devgrp.fitnesswebapp.dto.ResponseDTO;
import com.devgrp.fitnesswebapp.service.UserService;
import com.devgrp.fitnesswebapp.util.VarList;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping(value = "ap1/v1/goal")
public class GoalController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getGoals(@RequestParam( name = "userId") Integer userId ) {
        ResponseDTO result = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        try {
            var r = userService.getAllGoals(userId);
            result.setContent(r);
            result.setCode(VarList.RSP_SUCCESS);
            result.setMessage("Success");
            status = HttpStatus.OK;
        }
        catch ( Exception e ) {
            result.setContent(null);
            result.setCode(VarList.RSP_ERROR);
            result.setMessage(e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(result, status);
    }

//    @GetMapping( value = "/{id}" )
//    public ResponseEntity<ResponseDTO> getGoal( @PathVariable Integer id, @RequestParam( name = "userId") Integer userId ) {
//        ResponseDTO result = new ResponseDTO();
//        HttpStatus status = HttpStatus.UNAUTHORIZED;
//
//        try {
//            var r = userService.getGoal(userId, id);
//            result.setContent(r);
//            result.setCode(VarList.RSP_SUCCESS);
//            result.setMessage("Success");
//            status = HttpStatus.OK;
//        }
//        catch ( Exception e ) {
//            result.setContent(null);
//            result.setCode(VarList.RSP_ERROR);
//            result.setMessage(e.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//            System.out.println(e.getMessage());
//        }
//
//        return new ResponseEntity<>(result, status);
//    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createGoal( @RequestBody GoalDTO data, @RequestParam(name = "userId") Integer userId ) {
        ResponseDTO result = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        try {
            var r = userService.addGoal(userId, data);
            result.setContent(r);
            result.setCode(VarList.RSP_SUCCESS);
            result.setMessage("Success");
            status = HttpStatus.OK;
        }
        catch ( Exception e ) {
            result.setContent(null);
            result.setCode(VarList.RSP_ERROR);
            result.setMessage(e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(result, status);
    }

    @PutMapping( value = "/{id}" )
    public ResponseEntity<ResponseDTO> updateGoal( @PathVariable Integer id, @RequestBody GoalDTO data, @RequestParam( name = "userId") Integer userId ) {
        ResponseDTO result = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        try {
            var r = userService.updateGoal(id, data);
            result.setContent(r);
            result.setCode(VarList.RSP_SUCCESS);
            result.setMessage("Success");
            status = HttpStatus.OK;
        }
        catch ( Exception e ) {
            result.setContent(null);
            result.setCode(VarList.RSP_ERROR);
            result.setMessage(e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(result, status);
    }

    @DeleteMapping( value = "/{id}" )
    public ResponseEntity<ResponseDTO> deleteGoal( @PathVariable Integer id, @RequestParam( name = "userId") Integer userId ) {
        ResponseDTO result = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        try {
            var r = userService.deleteGoal(userId, id);
            result.setContent(r);
            result.setCode(VarList.RSP_SUCCESS);
            result.setMessage("Success");
            status = HttpStatus.OK;
        }
        catch ( Exception e ) {
            result.setContent(null);
            result.setCode(VarList.RSP_ERROR);
            result.setMessage(e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(result, status);
    }

    @GetMapping( value = "/{id}/progress" )
    public ResponseEntity<ResponseDTO> getGoalProgress( @PathVariable Integer id, @RequestParam( name = "userId") Integer userId ) {
        ResponseDTO result = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        try {
            var r = userService.getGoalDailyProgress(id);
            result.setContent(r);
            result.setCode(VarList.RSP_SUCCESS);
            result.setMessage("Success");
            status = HttpStatus.OK;
        }
        catch ( Exception e ) {
            result.setContent(null);
            result.setCode(VarList.RSP_ERROR);
            result.setMessage(e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(result, status);
    }

    @PostMapping( value = "/{id}/progress" )
    public ResponseEntity<ResponseDTO> createGoalProgress( @PathVariable Integer id, @RequestBody GoalDTO data, @RequestParam( name = "userId") Integer userId ) {
        ResponseDTO result = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        try {
            var r = userService.addDailyProgress(userId, id, data);
            result.setContent(r);
            result.setCode(VarList.RSP_SUCCESS);
            result.setMessage("Success");
            status = HttpStatus.OK;
        }
        catch ( Exception e ) {
            result.setContent(null);
            result.setCode(VarList.RSP_ERROR);
            result.setMessage(e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(result, status);
    }
}
