package com.biclapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "rutas")
public class Rutas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_usuario;

    private String nombre;

    private String ruta;

    private String estado;

    private String tipo_ruta;

    private String datos_origen;

    private String datos_destino;

    public Rutas() {
    }

    public Rutas(Long id, Long id_usuario, String nombre, String ruta, String estado, String tipo_ruta, String datos_origen, String datos_destino) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.ruta = ruta;
        this.estado = estado;
        this.tipo_ruta = tipo_ruta;
        this.datos_origen = datos_origen;
        this.datos_destino = datos_destino;
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

    public String getTipo_ruta() {
        return tipo_ruta;
    }

    public void setTipo_ruta(String tipo_ruta) {
        this.tipo_ruta = tipo_ruta;
    }

    public String getDatos_origen() {
        return datos_origen;
    }

    public void setDatos_origen(String datos_origen) {
        this.datos_origen = datos_origen;
    }

    public String getDatos_destino() {
        return datos_destino;
    }

    public void setDatos_destino(String datos_destino) {
        this.datos_destino = datos_destino;
    }
}
