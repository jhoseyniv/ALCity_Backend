package com.alcity.service.customexception;

public class ALCityResponseObject {
    private Integer code;

    private String status; //ok or error
    private Long recordId;
    private String  message;

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



    public ALCityResponseObject() {
    }

    public ALCityResponseObject(Integer code, String status, Long recordId, String message) {
        this.code = code;
        this.status = status;
        this.recordId = recordId;
        this.message = message;
    }
}
