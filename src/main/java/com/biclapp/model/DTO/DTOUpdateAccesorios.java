package com.biclapp.model.DTO;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class DTOUpdateAccesorios {

    @Size(min = 3, max = 10, message = "El código debe tener entre 3 y 10 caracteres")
    private String codigo;

    @NotEmpty(message = "Nombre de accesorio requerido")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;

    @NotEmpty(message = "Descripción de accesorio requerida")
    @Size(min = 1, max = 255, message = "La descripción debe tener entre 1 y 255 caracteres")
    private String descripcion;

    @Size(max = 255, message = "La foto tiene demasiados caracteres")
    private String foto;

    @NotEmpty(message = "Tipo de accesorio requerido")
    @Size(min = 1, max = 50, message = "El tipo debe tener entre 1 y 50 caracteres")
    private String tipo;

    @PositiveOrZero(message = "El valor del stock debe ser mayor o igual a 0")
    private Integer stock;

    @PositiveOrZero(message = "El valor del precio debe ser mayor o igual a 0")
    @DecimalMax(value = "999999.99", message = "El precio tiene un valor no admitido")
    private Double precio;

    private String estado;

    public DTOUpdateAccesorios() {
    }

    public DTOUpdateAccesorios(String codigo, String nombre, String descripcion, String foto, String tipo, Integer stock, Double precio, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.tipo = tipo;
        this.stock = stock;
        this.precio = precio;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
