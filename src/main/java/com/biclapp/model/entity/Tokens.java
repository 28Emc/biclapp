package com.biclapp.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class Tokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String tipoAccion;
    private String token;
    private int codigo;
    private LocalDateTime fechaCreacion;

    public Tokens() {
    }

    public Tokens(Long id, String email, String tipoAccion, String token, int codigo, LocalDateTime fechaCreacion) {
        this.id = id;
        this.email = email;
        this.tipoAccion = tipoAccion;
        this.token = token;
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
    }

    public Tokens(String email, String tipoAccion, String token, int codigo, LocalDateTime fechaCreacion) {
        this.email = email;
        this.tipoAccion = tipoAccion;
        this.token = token;
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
