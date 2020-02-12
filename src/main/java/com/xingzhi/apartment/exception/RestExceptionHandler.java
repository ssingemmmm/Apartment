package com.xingzhi.apartment.exception;
/*
 *  Copyright 2019, Liwei Wang <daveywang@live.com>.
 *  All rights reserved.
 *  Author: Liwei Wang
 *  Date: 06/2019
 */
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getFormattedMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getFormattedMessage());
    }
}