package com.alcity.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;


@ControllerAdvice
public class ObjectManagmentException extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ApplicationMemberNotFoundException.class)
    public ResponseEntity<Object> handleAnimalNotFoundException(ApplicationMemberNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage() );
        body.put("Cart", ex.getUsername() );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UniqueConstraintException.class)
    public ResponseEntity<Object> handleUniqueConstraintException(UniqueConstraintException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage() );
        body.put("unique", request );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


}
