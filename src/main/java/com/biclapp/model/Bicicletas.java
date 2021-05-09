package com.biclapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bicicletas {
    @Id
    private Long id;
    private Long id_local;
    private String marca;
    private String modelo;
    private Integer stock;
    private String caracteristicas;
    private String estado;
    private String foto;

    public Bicicletas() {
    }

    public Bicicletas(Long id, Long id_local, String marca, String modelo, Integer stock, String caracteristicas, String estado, String foto) {
        this.id = id;
        this.id_local = id_local;
        this.marca = marca;
        this.modelo = modelo;
        this.stock = stock;
        this.caracteristicas = caracteristicas;
        this.estado = estado;
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_local() {
        return id_local;
    }

    public void setId_local(Long id_local) {
        this.id_local = id_local;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
