package com.personal.project.controllers.advice;

import com.personal.project.pojo.errors.ErrorResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    static HttpHeaders defaultHeader = new HttpHeaders();

    @PostConstruct
    static void init() {
        defaultHeader.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handle(HttpRequestMethodNotSupportedException ex) {
        Throwable root = NestedExceptionUtils.getMostSpecificCause(ex);
        log.error("HttpRequestMethodNotSupportedException Exception: ", root);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(Instant.now())
                .build();

        return new ResponseEntity<>(errorResponse, defaultHeader,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        Throwable root = NestedExceptionUtils.getMostSpecificCause(ex);
        log.error("Root Exception: ", root);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(errorResponse, defaultHeader ,HttpStatus.BAD_REQUEST);
    }
}
