package com.biclapp.model.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

public class DTOUpdateAverias {

    @NotNull(message = "Usuario requerido")
    private Long id_usuario;

    @Positive(message = "El código debe ser mayor a 0")
    private Integer codigo;

    @NotEmpty(message = "Dirección requerida")
    @Size(min = 1, max = 150, message = "La dirección debe tener entre 1 y 150 caracteres")
    private String direccion;

    @NotEmpty(message = "Motivo requerido")
    @Size(min = 1, max = 255, message = "El motivo debe tener entre 1 y 255 caracteres")
    private String motivo;

    @NotNull(message = "Fecha registro requerida")
    private LocalDateTime fecha_registro;

    private LocalDateTime fecha_atencion;

    private String estado;

    public DTOUpdateAverias() {
    }

    public DTOUpdateAverias(Long id_usuario, Integer codigo, String direccion, String motivo, LocalDateTime fecha_registro, LocalDateTime fecha_atencion, String estado) {
        this.id_usuario = id_usuario;
        this.codigo = codigo;
        this.direccion = direccion;
        this.motivo = motivo;
        this.fecha_registro = fecha_registro;
        this.fecha_atencion = fecha_atencion;
        this.estado = estado;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public LocalDateTime getFecha_atencion() {
        return fecha_atencion;
    }

    public void setFecha_atencion(LocalDateTime fecha_atencion) {
        this.fecha_atencion = fecha_atencion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
