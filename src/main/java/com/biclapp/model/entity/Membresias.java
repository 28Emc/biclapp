package com.biclapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "membresias")
public class Membresias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codigo;

    @NotEmpty(message = "Tipo de membresía requerida")
    @Size(min = 1, max = 100, message = "El tipo de membresía tener entre 1 y 100 caracteres")
    private String tipo;

    @NotNull(message = "Cuota requerida")
    @PositiveOrZero(message = "El valor de la cuota debe ser mayor o igual a 0")
    @DecimalMax(value = "999999.99", message = "La cuota tiene un valor no admitido")
    private Double cuota;

    public Membresias() {
    }

    public Membresias(Long id, Integer codigo, String tipo, Double cuota) {
        this.id = id;
        this.codigo = codigo;
        this.tipo = tipo;
        this.cuota = cuota;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getCuota() {
        return cuota;
    }

    public void setCuota(Double cuota) {
        this.cuota = cuota;
    }
}

