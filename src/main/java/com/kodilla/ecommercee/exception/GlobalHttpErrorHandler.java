package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<Object> handlerGroupNotFoundException(GroupNotFoundException exception) {
        return new ResponseEntity<>("Group with this ID do not exist", HttpStatus.BAD_REQUEST);
    }
}
