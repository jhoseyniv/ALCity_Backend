package com.alcity.customexception;

public class ALCityReturnObject {
    private Long status;
    private Long recordId;
    private String  message;
    private String  recordTitle;

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

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public ALCityReturnObject() {
    }


    public ALCityReturnObject(Long status , Long recordId, String message, String recordTitle) {
        this.recordId = recordId;
        this.message = message;
        this.recordTitle = recordTitle;
        this.status = status;
    }
}
