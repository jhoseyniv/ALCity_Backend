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
public class ObjectManagementException extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message ", "This record Id has  not found in the database ...." );
        body.put("Record Id", ex.getRecordId() );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseObject.class)
    public ResponseEntity<Object> handleUniqueConstraintException(ResponseObject ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", ex.getType() );
        body.put("status", ex.getStatus() );
        body.put("entity", ex.getEntity() );
        body.put("message", ex.getMessage() );
        body.put("recordId", ex.getRecordId() );
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ViolateForeignKeyException.class)
    public ResponseEntity<Object> handleViolateForeignKeyException(ResponseObject ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", ex.getType() );
        body.put("status", ex.getStatus() );
        body.put("entity", ex.getEntity() );
        body.put("message", ex.getMessage() );
        body.put("recordId", ex.getRecordId() );
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(NotNullConstraintException.class)
    public ResponseEntity<Object> handleNotNullConstraintExceptionn(NotNullConstraintException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage() );
        body.put("Record", ex.getRecordData() );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


}
