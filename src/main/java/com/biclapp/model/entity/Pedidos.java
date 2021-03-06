package com.biclapp.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_usuario")
    private Long idUsuario;

    private Long id_empleado;

    private Integer codigo;

    private String estado;

    private String tipo_pedido;

    private String direccion;

    private LocalDateTime fecha_registro;

    private LocalDateTime fecha_entrega;

    private LocalDateTime fecha_actualizacion;

    private LocalDateTime fecha_devolucion;

    public Pedidos() {
    }

    public Pedidos(Long id, Long idUsuario, Long id_empleado, Integer codigo, String estado, String tipo_pedido, String direccion, LocalDateTime fecha_registro, LocalDateTime fecha_entrega, LocalDateTime fecha_actualizacion, LocalDateTime fecha_devolucion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.id_empleado = id_empleado;
        this.codigo = codigo;
        this.estado = estado;
        this.tipo_pedido = tipo_pedido;
        this.direccion = direccion;
        this.fecha_registro = fecha_registro;
        this.fecha_entrega = fecha_entrega;
        this.fecha_actualizacion = fecha_actualizacion;
        this.fecha_devolucion = fecha_devolucion;
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

    public Long getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Long id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_pedido() {
        return tipo_pedido;
    }

    public void setTipo_pedido(String tipo_pedido) {
        this.tipo_pedido = tipo_pedido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public LocalDateTime getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(LocalDateTime fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public LocalDateTime getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(LocalDateTime fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public LocalDateTime getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(LocalDateTime fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }
}
