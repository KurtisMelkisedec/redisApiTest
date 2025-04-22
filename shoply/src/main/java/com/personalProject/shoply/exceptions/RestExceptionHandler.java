package com.personalProject.shoply.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityAlreadyExists.class)
    public ResponseEntity<?> handleEntitiesAlreadyExist(EntityAlreadyExists exception){
        return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
    }
    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<?> handleEntitiesNotFound(EntityNotFound exception){
        return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
    }



}
