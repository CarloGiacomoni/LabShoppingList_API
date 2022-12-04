package com.carlogiacomoni.LabShoppingList_API.api.controller.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entidadeNotFoundException(EntityNotFoundException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        DefaultError body = DefaultError.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(e.getMessage())
                .build();

        return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<?> NomeAlreadyExists(EntityExistsException e, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        DefaultError body = DefaultError.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(e.getMessage())
                .build();

        return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
    }

}