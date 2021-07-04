package com.biclapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "recorridos")
public class Recorridos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codigo;

    @NotNull(message = "Usuario requerido")
    @Column(name = "id_usuario")
    private Long idUsuario;

    @PastOrPresent(message = "La fecha de registro es inválida")
    private LocalDateTime fecha_registro;

    private LocalDateTime fecha_actualizacion;

    @PositiveOrZero(message = "El valor de los kilómetros debe ser mayor o igual a 0")
    private Double kilometros;

    @PositiveOrZero(message = "El valor de las calorías debe ser mayor o igual a 0")
    private Double kcal;

    @PositiveOrZero(message = "El valor de las horas debe ser mayor o igual a 0")
    private Integer horas;

    @PositiveOrZero(message = "El valor de los minutos debe ser mayor o igual a 0")
    private Integer minutos;

    private String estado;

    public Recorridos() {
    }

    public Recorridos(Long id, Integer codigo, Long idUsuario, LocalDateTime fecha_registro, LocalDateTime fecha_actualizacion, Double kilometros, Double kcal, Integer horas, Integer minutos, String estado) {
        this.id = id;
        this.codigo = codigo;
        this.idUsuario = idUsuario;
        this.fecha_registro = fecha_registro;
        this.fecha_actualizacion = fecha_actualizacion;
        this.kilometros = kilometros;
        this.kcal = kcal;
        this.horas = horas;
        this.minutos = minutos;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public LocalDateTime getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(LocalDateTime fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public Double getKilometros() {
        return kilometros;
    }

    public void setKilometros(Double kilometros) {
        this.kilometros = kilometros;
    }

    public Double getKcal() {
        return kcal;
    }

    public void setKcal(Double kcal) {
        this.kcal = kcal;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
