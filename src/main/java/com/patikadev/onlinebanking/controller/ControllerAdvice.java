package com.patikadev.onlinebanking.controller;

import com.patikadev.onlinebanking.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    //for entity class annotation
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> databaseValidationException(DataIntegrityViolationException exception){
       return ResponseEntity.badRequest().body(new ApiError(exception.getLocalizedMessage()));
    }

    //for Validation
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> onConstraintViolationExceptionHandle(ConstraintViolationException ex){
     return ResponseEntity.badRequest().body( new ApiErrorValidation(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList())));
    }


    //for BaseException
   @ResponseStatus(value = HttpStatus.BAD_REQUEST)
   @ExceptionHandler(BaseException.class)
   public ResponseEntity<?> onBaseExceptionHandled(BaseException baseException) {
        log.info("errorMessage: {}",baseException.getMessage());
        return ResponseEntity.badRequest().body(new ApiError(baseException.getMessage()));
    }


    public record ApiError(String errorMessage) {
    }
    public record ApiErrorValidation(List errorMessage) {
    }
}
