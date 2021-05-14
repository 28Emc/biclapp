package com.biclapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "membresias")
public class Membresias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private Double cuota;

    public Membresias() {
    }

    public Membresias(Long id, String tipo, Double cuota) {
        this.id = id;
        this.tipo = tipo;
        this.cuota = cuota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

