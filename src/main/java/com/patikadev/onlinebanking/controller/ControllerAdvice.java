package com.patikadev.onlinebanking.controller;

import com.patikadev.onlinebanking.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
@Slf4j
public class ControllerAdvice extends ResponseEntityExceptionHandler {


    //for BaseException
   @ResponseStatus(value = HttpStatus.BAD_REQUEST)
   @ExceptionHandler(BaseException.class)
   public ResponseEntity<?> onBaseExceptionHandled(BaseException baseException) {
        log.info("errorMessage: {}",baseException.getMessage());
        return ResponseEntity.badRequest().body(new ApiError(baseException.getMessage()));
    }


    public record ApiError(String errorMessage) {
    }
}
