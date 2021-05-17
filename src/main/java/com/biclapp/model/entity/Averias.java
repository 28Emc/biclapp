package com.biclapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "averias")
public class Averias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "El código debe ser mayor a 0")
    private Integer codigo;

    @NotNull(message = "Usuario requerido")
    private Long id_usuario;

    @NotEmpty(message = "Dirección requerida")
    @Size(min = 1, max = 150, message = "La dirección debe tener entre 1 y 150 caracteres")
    private String direccion;

    @NotEmpty(message = "Motivo requerido")
    @Size(min = 1, max = 255, message = "El motivo debe tener entre 1 y 255 caracteres")
    private String motivo;

    @NotNull(message = "Fecha registro requerida")
    private Date fecha_registro;

    private String estado;

    public Averias() {
    }

    public Averias(Long id, Integer codigo, Long id_usuario, String direccion, String motivo, Date fecha_registro, String estado) {
        this.id = id;
        this.codigo = codigo;
        this.id_usuario = id_usuario;
        this.direccion = direccion;
        this.motivo = motivo;
        this.fecha_registro = fecha_registro;
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

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
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

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
