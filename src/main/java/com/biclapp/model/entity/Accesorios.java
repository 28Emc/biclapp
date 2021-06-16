package com.biclapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "accesorios")
public class Accesorios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "El código debe ser mayor a 0")
    private Integer codigo;

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

    private Integer puntos;

    private String estado;

    public Accesorios() {
    }

    public Accesorios(Long id, Integer codigo, String nombre, String descripcion, String foto, String tipo, Integer stock, Double precio, Integer puntos, String estado) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.tipo = tipo;
        this.stock = stock;
        this.precio = precio;
        this.puntos = puntos;
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

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
}
