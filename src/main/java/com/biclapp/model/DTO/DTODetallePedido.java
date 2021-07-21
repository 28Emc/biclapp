package com.biclapp.model.DTO;

import java.time.LocalDateTime;

public class DTODetallePedido {
    Long id;
    Long id_pedido;
    Long id_producto;
    String tipo_pedido;

    String marca_bicicleta;
    String modelo_bicicleta;

    String nombre_accesorio;
    String descripcion_accesorio;
    String tipo_accesorio;

    String foto; // ARTICULO O BICICLETA
    int cantidad; // SOLO ARTICULO
    double precio; // SOLO ARTICULO
    int puntos; // SOLO ARTICULO
    double total; // SOLO ARTICULO

    LocalDateTime fechaRegistro;
    LocalDateTime fechaDevolucion;

    public DTODetallePedido() {
    }

    public DTODetallePedido(Long id, Long id_pedido, Long id_producto, String tipo_pedido, String marca_bicicleta, String modelo_bicicleta, String nombre_accesorio, String descripcion_accesorio, String tipo_accesorio, String foto, int cantidad, double precio, int puntos, double total, LocalDateTime fechaRegistro, LocalDateTime fechaDevolucion) {
        this.id = id;
        this.id_pedido = id_pedido;
        this.id_producto = id_producto;
        this.tipo_pedido = tipo_pedido;
        this.marca_bicicleta = marca_bicicleta;
        this.modelo_bicicleta = modelo_bicicleta;
        this.nombre_accesorio = nombre_accesorio;
        this.descripcion_accesorio = descripcion_accesorio;
        this.tipo_accesorio = tipo_accesorio;
        this.foto = foto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.puntos = puntos;
        this.total = total;
        this.fechaRegistro = fechaRegistro;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public String getTipo_pedido() {
        return tipo_pedido;
    }

    public void setTipo_pedido(String tipo_pedido) {
        this.tipo_pedido = tipo_pedido;
    }

    public String getMarca_bicicleta() {
        return marca_bicicleta;
    }

    public void setMarca_bicicleta(String marca_bicicleta) {
        this.marca_bicicleta = marca_bicicleta;
    }

    public String getModelo_bicicleta() {
        return modelo_bicicleta;
    }

    public void setModelo_bicicleta(String modelo_bicicleta) {
        this.modelo_bicicleta = modelo_bicicleta;
    }

    public String getNombre_accesorio() {
        return nombre_accesorio;
    }

    public void setNombre_accesorio(String nombre_accesorio) {
        this.nombre_accesorio = nombre_accesorio;
    }

    public String getDescripcion_accesorio() {
        return descripcion_accesorio;
    }

    public void setDescripcion_accesorio(String descripcion_accesorio) {
        this.descripcion_accesorio = descripcion_accesorio;
    }

    public String getTipo_accesorio() {
        return tipo_accesorio;
    }

    public void setTipo_accesorio(String tipo_accesorio) {
        this.tipo_accesorio = tipo_accesorio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
