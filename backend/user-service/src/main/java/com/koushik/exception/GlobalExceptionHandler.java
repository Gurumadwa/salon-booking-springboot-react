package com.koushik.exception;

import com.koushik.payload.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> ExceptionResponse (Exception ex, WebRequest req){
        ExceptionResponse response = new ExceptionResponse();

        response.setMessage(ex.getMessage());
        response.setError(req.getDescription(false));
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

}
