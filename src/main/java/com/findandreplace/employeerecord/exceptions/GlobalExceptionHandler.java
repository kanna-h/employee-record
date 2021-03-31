package com.findandreplace.employeerecord.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.lang.reflect.Field;
import java.util.*;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                    .timestamp(new Date())
                                                    .message(ex.getMessage())
                                                    .build();
//      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity invalidArguments(MethodArgumentTypeMismatchException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                                    .timestamp(new Date())
                                    .message(ex.getMessage())
                                    .build());
    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            errors.put(((FieldError)err).getField(), err.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .timestamp(new Date())
                        .message("INVALID INPUT FIELDS")
                        .data(errors)
                        .build());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> globalException(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .build());
    }
}


//MethodArgumentNotValidException
//Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; nested exception is java.lang.NumberFormatException: For input string: