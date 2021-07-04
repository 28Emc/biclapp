package com.biclapp.model.DTO;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class DTOUpdateToken {
    @NotEmpty(message = "Nombres requeridos")
    @Size(min = 1, max = 100, message = "Los nombres deben tener entre 1 y 100 caracteres")
    private String email;

    @NotEmpty(message = "Tipo de acción requerida")
    @Size(min = 1, max = 10, message = "El tipo de acción deben tener entre 1 y 10 caracteres")
    private String tipo_accion;
    private String password;
    private int codigo;
    private LocalDateTime fechaCreacion;

    public DTOUpdateToken() {
    }

    public DTOUpdateToken(String email, String tipo_accion, String password, int codigo, LocalDateTime fechaCreacion) {
        this.email = email;
        this.tipo_accion = tipo_accion;
        this.password = password;
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo_accion() {
        return tipo_accion;
    }

    public void setTipo_accion(String tipo_accion) {
        this.tipo_accion = tipo_accion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
