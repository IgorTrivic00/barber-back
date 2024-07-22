package com.example.demo.advice;

import com.example.demo.dto.exception.ApiException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<ApiException> handleAuthenticationException(AuthenticationException e){
        return new ResponseEntity<>(new ApiException("Ne postoji korisnik sa ovim kredencijalima", "401", 401, LocalDateTime.now()), HttpStatusCode.valueOf(401));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ApiException> handleIllegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<>(new ApiException(e.getMessage(), "500", 500, LocalDateTime.now()), HttpStatusCode.valueOf(500));
    }

}
