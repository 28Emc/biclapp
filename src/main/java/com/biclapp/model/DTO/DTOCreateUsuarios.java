package com.biclapp.model.DTO;

public class DTOCreateUsuarios {
    private Long id_rol;
    private Long id_membresia;
    private String nombres;
    private String apellidos;
    private String nro_documento;
    private String celular;
    private String direccion;
    private String username;
    private String password;
    private String estado;
    private boolean isActivo;
    private String foto;

    public DTOCreateUsuarios() {
    }

    public DTOCreateUsuarios(Long id_rol, Long id_membresia, String nombres, String apellidos, String nro_documento, String celular, String direccion, String username, String password, String estado, boolean isActivo, String foto) {
        this.id_rol = id_rol;
        this.id_membresia = id_membresia;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nro_documento = nro_documento;
        this.celular = celular;
        this.direccion = direccion;
        this.username = username;
        this.password = password;
        this.estado = estado;
        this.isActivo = isActivo;
        this.foto = foto;
    }

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    public Long getId_membresia() {
        return id_membresia;
    }

    public void setId_membresia(Long id_membresia) {
        this.id_membresia = id_membresia;
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

    public boolean isActivo() {
        return isActivo;
    }

    public void setActivo(boolean activo) {
        isActivo = activo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}