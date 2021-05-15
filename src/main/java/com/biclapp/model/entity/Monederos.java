package com.biclapp.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "monederos")
public class Monederos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Usuario requerido")
    private Long id_usuario;

    @PositiveOrZero(message = "El valor del stock debe ser mayor o igual a 0")
    private Integer puntos;

    public Monederos() {
    }

    public Monederos(Long id, Long id_usuario, Integer puntos) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.puntos = puntos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
