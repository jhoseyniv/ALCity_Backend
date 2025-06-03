package com.alcity.service.customexception;

public class ViolateForeignKeyException extends  RuntimeException{
    private Integer code;
    private String status; //ok or error
    private String  message;
    private Long recordId;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public ViolateForeignKeyException(Integer code, String status, String message, Long recordId) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.recordId = recordId;
    }
}
