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

    private Integer codigo;

    @NotNull(message = "Usuario requerido")
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotNull(message = "Puntos requeridos")
    @PositiveOrZero(message = "El valor del stock debe ser mayor o igual a 0")
    private Integer puntos;

    public Monederos() {
    }

    public Monederos(Long id, Integer codigo, Long idUsuario, Integer puntos) {
        this.id = id;
        this.codigo = codigo;
        this.idUsuario = idUsuario;
        this.puntos = puntos;
    }

    public Monederos(Integer codigo, Long idUsuario, Integer puntos) {
        this.codigo = codigo;
        this.idUsuario = idUsuario;
        this.puntos = puntos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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
