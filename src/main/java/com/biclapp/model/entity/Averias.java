package com.biclapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
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
    @Column(name = "id_usuario")
    private Long idUsuario;

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

    public Averias() {
    }

    public Averias(Long id, Integer codigo, Long idUsuario, String direccion, String motivo, LocalDateTime fecha_registro, LocalDateTime fecha_atencion, String estado) {
        this.id = id;
        this.codigo = codigo;
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.motivo = motivo;
        this.fecha_registro = fecha_registro;
        this.fecha_atencion = fecha_atencion;
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
