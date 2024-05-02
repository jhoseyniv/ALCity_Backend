package com.alcity.customexception;

public class NotNullConstraintException extends  RuntimeException{
    private String recordData;
    private String message;
    private String  entity;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getRecordData() {
        return recordData;
    }

    public void setRecordData(String recordData) {
        this.recordData = recordData;
    }

    public NotNullConstraintException(String message,String recordData, String entity) {
        this.message = message;
        this.entity = entity;
        this.recordData = recordData;
    }
}
