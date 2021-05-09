package com.biclapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Locales {
    @Id
    private Long id;
    private String direccion;

    public Locales() {
    }

    public Locales(Long id, String direccion) {
        this.id = id;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
