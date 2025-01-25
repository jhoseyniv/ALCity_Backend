package com.alcity.security;

public class IdToken {
    private String id_token;

    IdToken(String idToken) {
        id_token = idToken;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

}
