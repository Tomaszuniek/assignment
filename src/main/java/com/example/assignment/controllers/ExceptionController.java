package com.example.assignment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({ IllegalAccessException.class })
    public ResponseEntity<Object> handleNoHandlerFoundException(WebRequest request) {
        return new ResponseEntity<>("Sorry! Access Denied to " + request.getDescription(false), HttpStatus.FORBIDDEN);
    }

}