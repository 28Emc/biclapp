package com.biclapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "locales")
public class Locales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_empresa;

    private String direccion;

    public Locales() {
    }

    public Locales(Long id, Long id_empresa, String direccion) {
        this.id = id;
        this.id_empresa = id_empresa;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
