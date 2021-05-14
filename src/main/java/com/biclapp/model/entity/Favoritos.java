package com.biclapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "favoritos")
public class Favoritos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_usuario;

    private String tipo_destino;

    private String nombre_coordenadas;

    private String descripcion;

    public Favoritos() {
    }

    public Favoritos(Long id, Long id_usuario, String tipo_destino, String nombre_coordenadas, String descripcion) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.tipo_destino = tipo_destino;
        this.nombre_coordenadas = nombre_coordenadas;
        this.descripcion = descripcion;
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

    public String getTipo_destino() {
        return tipo_destino;
    }

    public void setTipo_destino(String tipo_destino) {
        this.tipo_destino = tipo_destino;
    }

    public String getNombre_coordenadas() {
        return nombre_coordenadas;
    }

    public void setNombre_coordenadas(String nombre_coordenadas) {
        this.nombre_coordenadas = nombre_coordenadas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
