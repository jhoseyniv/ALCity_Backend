package com.alcity.customexception;

import com.alcity.entity.alenum.ErrorType;

public class ResponseMessage {
    private ErrorType type;
    private String status; //ok or error
    private String entity;
    private Long recordId;
    private String  message;

    public ErrorType getType() {
        return type;
    }

    public void setType(ErrorType type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseMessage() {
    }

    public ResponseMessage(ErrorType type, String status, String entity, Long recordId, String message) {
        this.type = type;
        this.status = status;
        this.entity = entity;
        this.recordId = recordId;
        this.message = message;
    }
}
