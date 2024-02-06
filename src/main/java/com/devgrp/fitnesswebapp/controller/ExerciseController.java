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

    private final ExerciseService exerciseService;

    private final ResponseDTO responseDTO;

//    public ResponseEntity<ResponseDTO> functionName() {
//        ResponseDTO response = new ResponseDTO();
//        HttpStatus status = HttpStatus.UNAUTHORIZED;
//        try {
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return new ResponseEntity<ResponseDTO>( response, status );
//    }

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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getExercise( @PathVariable Integer id ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        try {

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<ResponseDTO>( response, status );
    }

    @GetMapping()

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseDTO> addExercise(@RequestBody ExerciseDTO exerciseDTO) {
        try {
            String res = exerciseService.addExercise(exerciseDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(exerciseDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Allready added");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseDTO> getExercise(@RequestParam(name = "page") Integer page, @RequestParam(name = "noOfElements") Integer noOfElements) {
        try {
            List<ExerciseGetDTO> exerciseGetDTOList = exerciseService.getExercise(page, noOfElements);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("success");
            responseDTO.setContent(exerciseGetDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getCount")
    public ResponseEntity<ResponseDTO> getCount() {
        try {
            long count = exerciseService.getExerciseCount();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("success");
            responseDTO.setContent(count);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateExercise(@RequestBody ExerciseGetDTO exerciseGetDTO) {
        try {
            String res = exerciseService.updateExercise(exerciseGetDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(exerciseGetDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("01")) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Data not found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/search/{exerciseName}")
    public ResponseEntity<ResponseDTO> searchExercise(@PathVariable String exerciseName) {
        try {
            ExerciseGetDTO exerciseGetDTO = exerciseService.searchExercise(exerciseName);
            if (exerciseGetDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(exerciseGetDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Exercise not found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{exerciseName}")
    public ResponseEntity<ResponseDTO> deleteExercise(@PathVariable String exerciseName) {
        try {
            String res = exerciseService.deleteExercise(exerciseName);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("01")) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Data not found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //userReview
    @PostMapping(value = "/addExerciseReview")
    public ResponseEntity<ResponseDTO> addExerciseReview(@RequestBody EmailReview emailReview) {
        try {
            String res = exerciseService.addExerciseReview(emailReview.getUserEmail(),emailReview.getUserReviewDTO());
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(emailReview.getUserReviewDTO());
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }
            else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getExerciseReview")
    public ResponseEntity<ResponseDTO> getExerciseUserReview() {
        try {
            List<UserReviewGetDTO> userReviewGetDTOList = exerciseService.getExerciseUserReview();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("success");
            responseDTO.setContent(userReviewGetDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deleteExerciseUserReview/{exerciseUserReviewKey}")
    public ResponseEntity<ResponseDTO> deleteExerciseUserReview(@PathVariable ExerciseUserReviewKey exerciseUserReviewKey) {
        try {
            String res = exerciseService.deleteExerciseReview(exerciseUserReviewKey);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("01")) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Data not found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
