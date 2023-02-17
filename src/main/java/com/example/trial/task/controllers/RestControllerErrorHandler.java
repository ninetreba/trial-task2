package com.example.trial.task.controllers;

import com.example.trial.task.exception.BusinessRuntimeException;
import com.example.trial.task.models.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerErrorHandler {

    @ExceptionHandler(BusinessRuntimeException.class)
    public ResponseEntity<ErrorDto> handleBusinessException(BusinessRuntimeException ex){
        ErrorDto errorDto = new ErrorDto(ex.getErrorCodeEnum().getCode(), ex.getMessage());
        return new ResponseEntity<>(errorDto, ex.getErrorCode().getHttpStatus());
    }


}
