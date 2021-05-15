package com.biclapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "recorridos")
public class Recorridos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Usuario requerido")
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotEmpty(message = "Fecha de registro requerida")
    @PastOrPresent(message = "La fecha de registro es inválida")
    private Date fecha_registro;

    @PositiveOrZero(message = "El valor de los kilómetros debe ser mayor o igual a 0")
    private Integer kilometros;

    @Size(max = 100, message = "El ritmo cardíaco debe tener entre 1 y 100 caracteres")
    private String ritmo_cardiaco;

    @NotEmpty(message = "Calorías requeridas")
    @Size(max = 10, message = "Las calorías debe tener entre 1 y 10 caracteres")
    private String kcal;

    @PositiveOrZero(message = "El valor del peso debe ser mayor o igual a 0")
    @DecimalMax(value = "999.99", message = "El peso tiene un valor no admitido")
    private Double peso;

    @Size(max = 20, message = "El tiempo debe tener entre 1 y 20 caracteres")
    private String tiempo;

    private String estado;

    public Recorridos() {
    }

    public Recorridos(Long id, Long idUsuario, Date fecha_registro, Integer kilometros, String ritmo_cardiaco, String kcal, Double peso, String tiempo, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fecha_registro = fecha_registro;
        this.kilometros = kilometros;
        this.ritmo_cardiaco = ritmo_cardiaco;
        this.kcal = kcal;
        this.peso = peso;
        this.tiempo = tiempo;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
