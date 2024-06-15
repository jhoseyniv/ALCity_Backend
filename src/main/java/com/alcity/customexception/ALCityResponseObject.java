package com.alcity.customexception;

public class ALCityResponseObject {
    private Integer code;

    private String status; //ok or error
    private Long reocrdId;
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

    public Long getReocrdId() {
        return reocrdId;
    }

    public void setReocrdId(Long reocrdId) {
        this.reocrdId = reocrdId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public ALCityResponseObject() {
    }

    public ALCityResponseObject(Integer code, String status, Long reocrdId, String message) {
        this.code = code;
        this.status = status;
        this.reocrdId = reocrdId;
        this.message = message;
    }
}
