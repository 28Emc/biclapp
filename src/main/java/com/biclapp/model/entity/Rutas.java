package com.biclapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rutas")
public class Rutas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Usuario requerido")
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotEmpty(message = "Nombre requerido")
    @Size(min = 1, max = 255, message = "El nombre debe tener entre 1 y 255 caracteres")
    private String nombre;

    @NotEmpty(message = "Ruta requerida")
    @Size(min = 1, max = 255, message = "La ruta debe tener entre 1 y 255 caracteres")
    private String ruta;

    private String estado;

    @Size(max = 100, message = "La ruta debe tener entre 1 y 100 caracteres")
    private String tipo_ruta;

    @Size(max = 255, message = "El origen debe tener entre 1 y 255 caracteres")
    private String datos_origen;

    @Size(max = 255, message = "El destino debe tener entre 1 y 255 caracteres")
    private String datos_destino;

    public Rutas() {
    }

    public Rutas(Long id, Long idUsuario, String nombre, String ruta, String estado, String tipo_ruta, String datos_origen, String datos_destino) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.ruta = ruta;
        this.estado = estado;
        this.tipo_ruta = tipo_ruta;
        this.datos_origen = datos_origen;
        this.datos_destino = datos_destino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long id_usuario) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
