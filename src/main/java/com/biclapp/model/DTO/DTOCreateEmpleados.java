package com.biclapp.model.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DTOCreateEmpleados {

    private Integer codigo;

    @NotNull(message = "Local requerido")
    private Long id_local;

    @NotEmpty(message = "Nombres requeridos")
    @Size(min = 1, max = 100, message = "Los nombres deben tener entre 1 y 100 caracteres")
    private String nombres;

    @NotEmpty(message = "Apellidos requeridos")
    @Size(min = 1, max = 100, message = "Los apellidos deben tener entre 1 y 100 caracteres")
    private String apellidos;

    @NotEmpty(message = "DNI requerido")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 caracteres")
    private String nro_documento;

    @NotEmpty(message = "Celular requerido")
    @Size(min = 9, max = 9, message = "El celular debe tener 9 caracteres")
    private String celular;

    @NotEmpty(message = "Dirección requerida")
    @Size(min = 1, max = 200, message = "La dirección debe tener entre 1 y 200 caracteres")
    private String direccion;

    @NotEmpty(message = "Nombre de usuario requerido")
    @Size(min = 1, max = 50, message = "El nombre de usuario debe tener entre 1 y 50 caracteres")
    private String username;

    @NotEmpty(message = "Contraseña requerido")
    @Size(min = 1, max = 255, message = "La contraseña debe tener entre 1 y 255 caracteres")
    private String password;

    private String estado;

    @Size(max = 255, message = "La foto debe tener como máximo 255 caracteres")
    private String foto;

    public DTOCreateEmpleados() {
    }

    public DTOCreateEmpleados(Integer codigo, Long id_local, String nombres, String apellidos, String nro_documento, String celular, String direccion, String username, String password, String estado, String foto) {
        this.codigo = codigo;
        this.id_local = id_local;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nro_documento = nro_documento;
        this.celular = celular;
        this.direccion = direccion;
        this.username = username;
        this.password = password;
        this.estado = estado;
        this.foto = foto;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNro_documento() {
        return nro_documento;
    }

    public void setNro_documento(String nro_documento) {
        this.nro_documento = nro_documento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
