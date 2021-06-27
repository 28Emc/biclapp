package com.biclapp.model.DTO;

public class AuthenticationResponse {

    private final String token;
    private String message;
    private Long id;
    private String rol;

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public AuthenticationResponse(String token, String message, Long id, String rol) {
        this.token = token;
        this.message = message;
        this.id = id;
        this.rol = rol;
    }
}
