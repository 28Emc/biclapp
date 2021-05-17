package com.biclapp.model.DTO;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

public class DTOUpdateRecorridos {

    @NotNull(message = "Usuario requerido")
    private Long idUsuario;

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

    @FutureOrPresent(message = "La fecha de actualización debe ser mayor o igual a la fecha actual")
    private LocalDateTime fecha_actualizacion;

    public DTOUpdateRecorridos() {
    }

    public DTOUpdateRecorridos(Long idUsuario, Integer kilometros, String ritmo_cardiaco, String kcal, Double peso, String tiempo, String estado, LocalDateTime fecha_actualizacion) {
        this.idUsuario = idUsuario;
        this.kilometros = kilometros;
        this.ritmo_cardiaco = ritmo_cardiaco;
        this.kcal = kcal;
        this.peso = peso;
        this.tiempo = tiempo;
        this.estado = estado;
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public LocalDateTime getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(LocalDateTime fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }
}
