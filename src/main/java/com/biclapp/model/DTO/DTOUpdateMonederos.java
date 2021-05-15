package com.biclapp.model.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class DTOUpdateMonederos {

    @NotNull(message = "Usuario requerido")
    private Long id_usuario;

    @PositiveOrZero(message = "El valor del stock debe ser mayor o igual a 0")
    private Integer puntos;

    public DTOUpdateMonederos() {
    }

    public DTOUpdateMonederos(Long id_usuario, Integer puntos) {
        this.id_usuario = id_usuario;
        this.puntos = puntos;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
}
