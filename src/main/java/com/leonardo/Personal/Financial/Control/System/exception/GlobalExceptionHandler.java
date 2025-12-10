package com.leonardo.Personal.Financial.Control.System.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFound(EntityNotFound e, HttpServletRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return ResponseEntity.status(404).body(errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException e, HttpServletRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return ResponseEntity.status(400).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){

        List<String> listErrors = e.getBindingResult().getFieldErrors().stream().map(err -> err.getField() + ": " + err.getDefaultMessage()).toList();

        ErrorMessage errorMessage = new ErrorMessage(
                String.join(",", listErrors),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return ResponseEntity.status(400).body(errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage().contains("Duplicate entry") ? "Email already in use!" : "Data integrity violation.",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return ResponseEntity.status(400).body(errorMessage);
    }

}
