package com.biclapp.model.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DTOUpdateRutas {

    @NotNull(message = "Usuario requerido")
    private Long idUsuario;

    @NotEmpty(message = "Nombre requerido")
    @Size(min = 1, max = 255, message = "El nombre debe tener entre 1 y 255 caracteres")
    private String nombre;

    @NotEmpty(message = "Ruta requerida")
    @Size(min = 1, max = 255, message = "La ruta debe tener entre 1 y 255 caracteres")
    private String ruta;

    @Size(max = 100, message = "La ruta debe tener entre 1 y 100 caracteres")
    private String tipo_ruta;

    @Size(max = 255, message = "El origen debe tener entre 1 y 255 caracteres")
    private String datos_origen;

    @Size(max = 255, message = "El destino debe tener entre 1 y 255 caracteres")
    private String datos_destino;

    public DTOUpdateRutas() {
    }

    public DTOUpdateRutas(Long idUsuario, String nombre, String ruta, String tipo_ruta, String datos_origen, String datos_destino) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.ruta = ruta;
        this.tipo_ruta = tipo_ruta;
        this.datos_origen = datos_origen;
        this.datos_destino = datos_destino;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTipo_ruta() {
        return tipo_ruta;
    }

    public void setTipo_ruta(String tipo_ruta) {
        this.tipo_ruta = tipo_ruta;
    }

    public String getDatos_origen() {
        return datos_origen;
    }

    public void setDatos_origen(String datos_origen) {
        this.datos_origen = datos_origen;
    }

    public String getDatos_destino() {
        return datos_destino;
    }

    public void setDatos_destino(String datos_destino) {
        this.datos_destino = datos_destino;
    }
}
