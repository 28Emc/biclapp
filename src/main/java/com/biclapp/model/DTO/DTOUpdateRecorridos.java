package com.biclapp.model.DTO;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

public class DTOUpdateRecorridos {

    @NotNull(message = "Usuario requerido")
    private Long idUsuario;

    @PositiveOrZero(message = "El valor de los kilómetros debe ser mayor o igual a 0")
    private Double kilometros;

    private String estado;

    @FutureOrPresent(message = "La fecha de actualización debe ser mayor o igual a la fecha actual")
    private LocalDateTime fecha_actualizacion;

    public DTOUpdateRecorridos() {
    }

    public DTOUpdateRecorridos(Long idUsuario, Double kilometros, String estado, LocalDateTime fecha_actualizacion) {
        this.idUsuario = idUsuario;
        this.kilometros = kilometros;
        this.estado = estado;
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getKilometros() {
        return kilometros;
    }

    public void setKilometros(Double kilometros) {
        this.kilometros = kilometros;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(LocalDateTime fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }
}
