package com.biclapp.model.DTO;

public class AuthenticationResponse {

    private final String token;
    private String message;

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthenticationResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }
}
