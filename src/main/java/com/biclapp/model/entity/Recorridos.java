package com.biclapp.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "recorridos")
public class Recorridos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_usuario;

    private Date fecha_registro;

    private Integer kilometros;

    private String ritmo_cardiaco;

    private String kcal;

    private Double peso;

    private String tiempo;

    public Recorridos() {
    }

    public Recorridos(Long id, Long id_usuario, Date fecha_registro, Integer kilometros, String ritmo_cardiaco, String kcal, Double peso, String tiempo) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.fecha_registro = fecha_registro;
        this.kilometros = kilometros;
        this.ritmo_cardiaco = ritmo_cardiaco;
        this.kcal = kcal;
        this.peso = peso;
        this.tiempo = tiempo;
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

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Integer getKilometros() {
        return kilometros;
    }

    public void setKilometros(Integer kilometros) {
        this.kilometros = kilometros;
    }

    public String getRitmo_cardiaco() {
        return ritmo_cardiaco;
    }

    public void setRitmo_cardiaco(String ritmo_cardiaco) {
        this.ritmo_cardiaco = ritmo_cardiaco;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
