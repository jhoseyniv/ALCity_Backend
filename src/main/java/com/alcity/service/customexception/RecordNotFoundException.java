package com.alcity.service.customexception;

public class RecordNotFoundException extends  RuntimeException {

    private Long recordId;
    private String  message;
    private String  recordTitle;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RecordNotFoundException(Long recordId,String recordTitle, String errorMessage) {
        this.recordId = recordId;
        this.message =  errorMessage;
        this.recordTitle =recordTitle;
    }

}
