package com.biclapp.model.DTO;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class DTOUpdateDetallesPedido {

    @NotNull(message = "Pedido requerido")
    private Long id_pedido;

    @Positive(message = "El valor del stock debe ser mayor o igual a 1")
    private Integer cantidad;

    @PositiveOrZero(message = "El valor del precio debe ser mayor o igual a 0")
    @DecimalMax(value = "999999.99", message = "El precio tiene un valor no admitido")
    private Double precio;

    @PositiveOrZero(message = "El valor del total debe ser mayor o igual a 0")
    @DecimalMax(value = "999999.99", message = "El total tiene un valor no admitido")
    private Double total;

    public DTOUpdateDetallesPedido() {
    }

    public DTOUpdateDetallesPedido(Long id_pedido, Integer cantidad, Double precio, Double total) {
        this.id_pedido = id_pedido;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
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
}
