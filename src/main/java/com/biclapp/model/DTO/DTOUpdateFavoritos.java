package com.biclapp.model.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DTOUpdateFavoritos {

    @NotNull(message = "Usuario requerido")
    private Long id_usuario;

    @NotEmpty(message = "Tipo destino requerido")
    @Size(min = 1, max = 100, message = "El tipo de destino debe tener entre 1 y 100 caracteres")
    private String tipo_destino;

    @NotEmpty(message = "Nombre de coordenada requerido")
    @Size(min = 1, max = 100, message = "El nombre de coordenada debe tener entre 1 y 100 caracteres")
    private String nombre_coordenadas;

    @Size(max = 255, message = "La descripción debe tener entre 1 y 255 caracteres")
    private String descripcion;

    public DTOUpdateFavoritos() {
    }

    public DTOUpdateFavoritos(Long id_usuario, String tipo_destino, String nombre_coordenadas, String descripcion) {
        this.id_usuario = id_usuario;
        this.tipo_destino = tipo_destino;
        this.nombre_coordenadas = nombre_coordenadas;
        this.descripcion = descripcion;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTipo_destino() {
        return tipo_destino;
    }

    public void setTipo_destino(String tipo_destino) {
        this.tipo_destino = tipo_destino;
    }

    public String getNombre_coordenadas() {
        return nombre_coordenadas;
    }

    public void setNombre_coordenadas(String nombre_coordenadas) {
        this.nombre_coordenadas = nombre_coordenadas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
