package com.biclapp.model.DTO;

import com.biclapp.model.entity.DetallesPedido;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class DTOUpdatePedidos {

    @NotNull(message = "Pedido requerido")
    private Long id_pedido;

    @NotNull(message = "Usuario requerido")
    private Long id_usuario;

    @NotNull(message = "Empleado requerido")
    private Long id_empleado;

    @NotEmpty(message = "Tipo pedido requerido")
    @Size(min = 1, max = 45, message = "El tipo de pedido debe tener entre 1 y 45 caracteres")
    private String tipo_pedido;

    @NotEmpty(message = "Dirección requerida")
    @Size(min = 1, max = 150, message = "La dirección debe tener entre 1 y 150 caracteres")
    private String direccion;

    private LocalDateTime fecha_registro;

    @NotNull(message = "Detalles pedido requeridos")
    private List<DetallesPedido> detalles_pedido;

    public DTOUpdatePedidos() {
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
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

    public List<DetallesPedido> getDetalles_pedido() {
        return detalles_pedido;
    }

    public void setDetalles_pedido(List<DetallesPedido> detalles_pedido) {
        this.detalles_pedido = detalles_pedido;
    }
}
