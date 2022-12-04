package com.carlogiacomoni.LabShoppingList_API.api.controller.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefaultError {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
}