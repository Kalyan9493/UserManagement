package com.usermanagement.usermanagement.exceptionhandlers;

import com.usermanagement.usermanagement.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiResponse> handleCustomException(UserException ex) {
        return new ResponseEntity<>(
                new ApiResponse(
                        ex.getCode(),
                        ex.getMessage(),
                        null),
                HttpStatus.valueOf(ex.getCode())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(
                new ApiResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ex.getMessage(),
                        null),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
