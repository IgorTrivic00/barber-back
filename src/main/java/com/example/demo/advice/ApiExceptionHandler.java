package com.example.demo.advice;

import com.example.demo.dto.exception.ApiException;
import com.example.demo.dto.exception.ResourceNotFoundException;
import com.example.demo.dto.exception.SlotAlreadyAllocatedException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<ApiException> handleUsernameNotFoundException(UsernameNotFoundException e){
        return new ResponseEntity<>(new ApiException(e.getMessage(), "401", 401, LocalDateTime.now()), HttpStatusCode.valueOf(401));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ApiException> handleIllegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<>(new ApiException(e.getMessage(), "500", 500, LocalDateTime.now()), HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ApiException> handleRuntimeException(RuntimeException e){
        return new ResponseEntity<>(new ApiException(e.getMessage(), "500", 500, LocalDateTime.now()), HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ApiException> handleResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(new ApiException(e.getMessage(), "400", 400, LocalDateTime.now()), HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ApiException> handleBadCredentialsException(BadCredentialsException e){
        return new ResponseEntity<>(new ApiException("Korisnik ne postoji!", "401", 500, LocalDateTime.now()), HttpStatusCode.valueOf(401));
    }

    @ExceptionHandler(value = {SlotAlreadyAllocatedException.class})
    public ResponseEntity<ApiException> handleSlotAlreadyAllocatedException(SlotAlreadyAllocatedException e){
        return new ResponseEntity<>(new ApiException(e.getMessage(), "400", 400, LocalDateTime.now()), HttpStatusCode.valueOf(400));
    }

}
