package com.biclapp.model.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class DTOUpdateMonederos {

    @NotNull(message = "Usuario requerido")
    private Long idUsuario;

    @NotNull(message = "Puntos requeridos")
    @PositiveOrZero(message = "El valor del stock debe ser mayor o igual a 0")
    private Integer puntos;

    public DTOUpdateMonederos() {
    }

    public DTOUpdateMonederos(Long idUsuario, Integer puntos) {
        this.idUsuario = idUsuario;
        this.puntos = puntos;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
}
