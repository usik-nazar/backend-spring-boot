package com.nazar.backendspringboot.controller;

import com.nazar.backendspringboot.exception.IncorrectLoginOrPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<?> handle(RuntimeException e) {
        log.error("Error", e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(IncorrectLoginOrPasswordException.class)
    public ResponseEntity<?> handle401(RuntimeException e) {
        return ResponseEntity.status(UNAUTHORIZED).build();
    }
}
