package com.findandreplace.employeerecord.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@RestControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<?> resourceNotFoundException(Exception ex, WebRequest req){
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                    .timestamp(new Date())
                                                    .message(ex.getMessage())
                                                    .data(req.getDescription(false))
                                                    .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
