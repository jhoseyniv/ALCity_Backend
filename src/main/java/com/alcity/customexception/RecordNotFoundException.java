package com.alcity.customexception;

public class RecordNotFoundException extends  RuntimeException {

    private String username;
    private String  message;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RecordNotFoundException(String username, String message) {
        this.username = username;
        this.message =  message;
    }

}
