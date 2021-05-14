package com.biclapp.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_usuario;
    private Long id_empleado;
    private Long id_bicicleta;
    private String codigo;
    private String estado;
    private String tipo_pedido;
    private String direccion;
    private Date fecha_registro;

    public Pedidos() {
    }

    public Pedidos(Long id, Long id_usuario, Long id_empleado, Long id_bicicleta, String codigo, String estado, String tipo_pedido, String direccion, Date fecha_registro) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_empleado = id_empleado;
        this.id_bicicleta = id_bicicleta;
        this.codigo = codigo;
        this.estado = estado;
        this.tipo_pedido = tipo_pedido;
        this.direccion = direccion;
        this.fecha_registro = fecha_registro;
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

    public Long getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Long id_empleado) {
        this.id_empleado = id_empleado;
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

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
