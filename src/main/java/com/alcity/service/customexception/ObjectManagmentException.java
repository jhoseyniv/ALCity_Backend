package com.alcity.service.customexception;

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


    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message ", "This record Id has  not found in the database ...." );
        body.put("Record Id", ex.getRecordId() );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UniqueConstraintException.class)
    public ResponseEntity<Object> handleUniqueConstraintException(UniqueConstraintException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp:", LocalDateTime.now());
        body.put("message ", "This record Id and Value is Already exist, Unique constraint...." );
        body.put("Record Value ", ex.getRecordData() );
        body.put("Record Id ", ex.getRecordId() );
        body.put("Object Name ", ex.getDatabaseEntity() );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ViolateForeignKeyException.class)
    public ResponseEntity<Object> handleViolateForeignKeyException(ViolateForeignKeyException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp:", LocalDateTime.now());
        body.put("message ", "This record Id has a many related data in databes , violates foreign key constraint...." );
        body.put("Record Value ", ex.getRecordData() );
        body.put("Record Id ", ex.getRecordId() );
        body.put("Object Name ", ex.getDatabaseEntity() );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
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
