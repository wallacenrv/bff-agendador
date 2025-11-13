package com.wallacen.bff.agendador.controller;

import com.wallacen.bff.agendador.business.exception.ConflictException;
import com.wallacen.bff.agendador.business.exception.ResourceNotFoundException;
import com.wallacen.bff.agendador.business.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//aqui sao as excecoes globais
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflictException(ConflictException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleConflictException(UnauthorizedException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}


