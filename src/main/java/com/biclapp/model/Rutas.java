package com.biclapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rutas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_usuario;
    private Long id_bicicleta;
    private String nombre;
    private String ruta;
    private String estado;

    public Rutas() {
    }

    public Rutas(Long id, Long id_usuario, Long id_bicicleta, String nombre, String ruta, String estado) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_bicicleta = id_bicicleta;
        this.nombre = nombre;
        this.ruta = ruta;
        this.estado = estado;
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

    public Long getId_bicicleta() {
        return id_bicicleta;
    }

    public void setId_bicicleta(Long id_bicicleta) {
        this.id_bicicleta = id_bicicleta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
