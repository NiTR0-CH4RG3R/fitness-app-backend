package com.devgrp.fitnesswebapp.controller;

import com.devgrp.fitnesswebapp.dto.ExerciseDTO;
import com.devgrp.fitnesswebapp.dto.ExerciseGetDTO;
import com.devgrp.fitnesswebapp.dto.ResponseDTO;
import com.devgrp.fitnesswebapp.dto.UserReviewGetDTO;
import com.devgrp.fitnesswebapp.entity.compositekeys.ExerciseUserReviewKey;
import com.devgrp.fitnesswebapp.service.ExerciseService;
import com.devgrp.fitnesswebapp.util.EmailReview;
import com.devgrp.fitnesswebapp.util.PageElement;
import com.devgrp.fitnesswebapp.util.VarList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping(value = "ap1/v1/exercise")
public class ExerciseController {

    @Autowired
    private final ExerciseService exerciseService;

    @GetMapping( value = "/all" )
    public ResponseEntity<ResponseDTO> getExercises() {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        try {

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<ResponseDTO>( response, status );
    }


    @GetMapping
    public ResponseEntity<ResponseDTO> getExercisesPage( @RequestParam(name = "page") Integer page, @RequestParam(name = "noOfElements") Integer noOfElements ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;

        try {
            var result = exerciseService.getExercise(page, noOfElements);
            response.setContent(result);
            response.setMessage("Successful");
            response.setCode(VarList.RSP_SUCCESS);
            status = HttpStatus.OK;
        }
        catch (Exception e) {
            response.setCode(VarList.RSP_ERROR);
            response.setMessage("Error");
            response.setContent(null);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<ResponseDTO>(response, status);
    }

    @GetMapping(value = "/count")
    public ResponseEntity<ResponseDTO> getExerciseCount() {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;

        try {
            var result = exerciseService.getExerciseCount();
            response.setContent(result);
            response.setMessage("Successful");
            response.setCode(VarList.RSP_SUCCESS);
            status = HttpStatus.OK;
        }
        catch (Exception e) {
            response.setCode(VarList.RSP_ERROR);
            response.setMessage("Error");
            response.setContent(null);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<ResponseDTO>(response, status);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> getExercise( @PathVariable Integer id ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        try {

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            response.setCode(VarList.RSP_ERROR);
            response.setMessage(e.getMessage());
            response.setContent(null);
        }
        return new ResponseEntity<ResponseDTO>( response, status );
    }

    @GetMapping(value = "/name/{name}" )
    public ResponseEntity<ResponseDTO> getExerciseByName( @PathVariable String name ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        try {
            var result = exerciseService.searchExercise( name );
            response.setContent(result);
            response.setMessage("Successful");
            response.setCode(VarList.RSP_SUCCESS);
            status = HttpStatus.OK;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            response.setCode(VarList.RSP_ERROR);
            response.setMessage(e.getMessage());
            response.setContent(null);
        }
        return new ResponseEntity<ResponseDTO>( response, status );
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> createExercise(@RequestBody ExerciseDTO data, @RequestParam Integer userId) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        try {
            var result = exerciseService.addExercise( data );
            response.setContent(result);
            response.setMessage("Successful");
            response.setCode(VarList.RSP_SUCCESS);
            status = HttpStatus.OK;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            response.setCode(VarList.RSP_ERROR);
            response.setMessage(e.getMessage());
            response.setContent(null);
        }
        return new ResponseEntity<ResponseDTO>( response, status );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> updateExercise(@PathVariable Integer id, @RequestBody ExerciseDTO data, @RequestParam Integer userId) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        try {
            var result = exerciseService.updateExercise( id, data );
            response.setContent(result);
            response.setMessage("Successful");
            response.setCode(VarList.RSP_SUCCESS);
            status = HttpStatus.OK;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            response.setCode(VarList.RSP_ERROR);
            response.setMessage(e.getMessage());
            response.setContent(null);
        }
        return new ResponseEntity<ResponseDTO>( response, status );
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> deleteExercise(@PathVariable Integer id, @RequestParam Integer userId) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        try {
            var result = exerciseService.deleteExercise( id );
            response.setContent(result);
            response.setMessage("Successful");
            response.setCode(VarList.RSP_SUCCESS);
            status = HttpStatus.OK;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            response.setCode(VarList.RSP_ERROR);
            response.setMessage(e.getMessage());
            response.setContent(null);
        }
        return new ResponseEntity<ResponseDTO>( response, status );
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<ResponseDTO> getExerciseReviews(@PathVariable Integer id) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        try {
            var result = exerciseService.getExerciseUserReview( id );
            response.setContent(result);
            response.setMessage("Successful");
            response.setCode(VarList.RSP_SUCCESS);
            status = HttpStatus.OK;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            response.setCode(VarList.RSP_ERROR);
            response.setMessage(e.getMessage());
            response.setContent(null);
        }
        return new ResponseEntity<ResponseDTO>( response, status );
    }

}
