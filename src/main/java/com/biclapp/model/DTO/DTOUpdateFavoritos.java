package com.biclapp.model.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DTOUpdateFavoritos {

    @NotNull(message = "Código requerido")
    private Integer codigo;

    @NotNull(message = "Usuario requerido")
    private Long idUsuario;

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

    public DTOUpdateFavoritos(Integer codigo, Long idUsuario, String tipo_destino, String nombre_coordenadas, String descripcion) {
        this.codigo = codigo;
        this.idUsuario = idUsuario;
        this.tipo_destino = tipo_destino;
        this.nombre_coordenadas = nombre_coordenadas;
        this.descripcion = descripcion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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
