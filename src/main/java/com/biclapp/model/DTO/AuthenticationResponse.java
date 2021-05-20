package com.biclapp.model.DTO;

public class AuthenticationResponse {

    private final String token;

    public String getToken() {
        return token;
    }

    public AuthenticationResponse(String token) {
        this.token = token;
    }
}
