package ru.unibell.tarasov.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<String> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        LOGGER.error("Invalid request occurred: {}, Request Details: {}", errors, request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(getErrorsMap(errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {

        LOGGER.error("Exception occurred: {}, Request Details: {}", ex.getMessage(), request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
