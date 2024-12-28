package com.alcity.customexception;

public class ALCityAcessRight {
    private Long id;
    private String username;
    private Integer code;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ALCityAcessRight() {
    }

    public ALCityAcessRight(Long id, String username, Integer code,String message) {
        this.id = id;
        this.username = username;
        this.code = code;
        this.message = message;
    }
}
