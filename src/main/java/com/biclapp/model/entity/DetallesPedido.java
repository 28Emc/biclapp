package com.biclapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "detalles_pedido")
public class DetallesPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Producto requerido")
    private Long id_producto;

    @NotNull(message = "Pedido requerido")
    @Column(name = "id_pedido")
    private Long idPedido;

    @Positive(message = "El valor del stock debe ser mayor o igual a 1")
    private Integer cantidad;

    @PositiveOrZero(message = "El valor del precio debe ser mayor o igual a 0")
    @DecimalMax(value = "999999.99", message = "El precio tiene un valor no admitido")
    private Double precio;

    @PositiveOrZero(message = "El valor de los puntos debe ser mayor o igual a 0")
    @DecimalMax(value = "999999", message = "El punto tiene un valor no admitido")
    private Integer puntos;

    @PositiveOrZero(message = "El valor del total debe ser mayor o igual a 0")
    @DecimalMax(value = "999999.99", message = "El total tiene un valor no admitido")
    private Double total;

    public DetallesPedido() {
    }

    public DetallesPedido(Long id, Long id_producto, Long idPedido, Integer cantidad, Double precio, Integer puntos, Double total) {
        this.id = id;
        this.id_producto = id_producto;
        this.idPedido = idPedido;
        this.cantidad = cantidad;
        this.precio = precio;
        this.puntos = puntos;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
}
