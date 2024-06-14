package com.alcity.customexception;

public class ALCityReturnObject {
    private Integer code;

    private String status; //ok or error
    private Long newReocrdId;
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

    public Long getNewReocrdId() {
        return newReocrdId;
    }

    public void setNewReocrdId(Long newReocrdId) {
        this.newReocrdId = newReocrdId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public ALCityReturnObject() {
    }

    public ALCityReturnObject(Integer code, String status, Long newReocrdId, String message) {
        this.code = code;
        this.status = status;
        this.newReocrdId = newReocrdId;
        this.message = message;
    }
}
