package com.biclapp.model.DTO;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class DTOCreateBicicletas {

    @Positive(message = "El valor del stock debe ser mayor a 0")
    private Integer codigo;

    @NotNull(message = "Local requerido")
    private Long id_local;

    @NotEmpty(message = "Marca requerida")
    @Size(min = 1, max = 50, message = "La marca debe tener entre 1 y 50 caracteres")
    private String marca;

    @NotEmpty(message = "Modelo requerido")
    @Size(min = 1, max = 50, message = "El modelo debe tener entre 1 y 50 caracteres")
    private String modelo;

    @NotNull(message = "Stock requerido")
    @PositiveOrZero(message = "El valor del stock debe ser mayor o igual a 0")
    private Integer stock;

    private String descripcion;

    private String estado;

    private MultipartFile foto;

    @Size(max = 45, message = "El color debe tener como máximo 45 caracteres")
    private String color;

    public DTOCreateBicicletas() {
    }

    public DTOCreateBicicletas(Integer codigo, Long id_local, String marca, String modelo, Integer stock, String descripcion, String estado, MultipartFile foto, String color) {
        this.codigo = codigo;
        this.id_local = id_local;
        this.marca = marca;
        this.modelo = modelo;
        this.stock = stock;
        this.descripcion = descripcion;
        this.estado = estado;
        this.foto = foto;
        this.color = color;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Long getId_local() {
        return id_local;
    }

    public void setId_local(Long id_local) {
        this.id_local = id_local;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public MultipartFile getFoto() {
        return foto;
    }

    public void setFoto(MultipartFile foto) {
        this.foto = foto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
