package com.biclapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_usuario;
    private Long id_bicicleta;
    private String codigo;
    private String estado;

    public Pedidos() {
    }

    public Pedidos(Long id, Long id_usuario, Long id_bicicleta, String codigo, String estado) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_bicicleta = id_bicicleta;
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
