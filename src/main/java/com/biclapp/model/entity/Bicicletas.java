package com.biclapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "bicicletas")
public class Bicicletas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_local;

    private String marca;

    private String modelo;

    private Integer stock;

    private String descripcion;

    private String estado;

    private String foto;

    private String color;

    public Bicicletas() {
    }

    public Bicicletas(Long id, Long id_local, String marca, String modelo, Integer stock, String descripcion, String estado, String foto, String color) {
        this.id = id;
        this.id_local = id_local;
        this.marca = marca;
        this.modelo = modelo;
        this.stock = stock;
        this.descripcion = descripcion;
        this.estado = estado;
        this.foto = foto;
        this.color = color;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
