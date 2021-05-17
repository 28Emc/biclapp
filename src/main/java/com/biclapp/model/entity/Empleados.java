package com.biclapp.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "empleados")
public class Empleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Código requerido")
    private Integer codigo;

    @NotNull(message = "Rol requerido")
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Roles rol;

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

    @NotNull(message = "Estado requerido")
    private String estado;

    @Size(max = 255, message = "La foto debe tener como máximo 255 caracteres")
    private String foto;

    private boolean isActivo;

    public Empleados() {
    }

    public Empleados(Long id, Integer codigo, Roles rol, String nombres, String apellidos, String nro_documento, String celular, String direccion, String username, String password, String estado, String foto, boolean isActivo) {
        this.id = id;
        this.codigo = codigo;
        this.rol = rol;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nro_documento = nro_documento;
        this.celular = celular;
        this.direccion = direccion;
        this.username = username;
        this.password = password;
        this.estado = estado;
        this.foto = foto;
        this.isActivo = isActivo;
    }

    public Empleados(Integer codigo, Roles rol, String nombres, String apellidos, String nro_documento, String celular, String direccion, String username, String password, String estado, String foto, boolean isActivo) {
        this.codigo = codigo;
        this.rol = rol;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nro_documento = nro_documento;
        this.celular = celular;
        this.direccion = direccion;
        this.username = username;
        this.password = password;
        this.estado = estado;
        this.foto = foto;
        this.isActivo = isActivo;
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

    @JsonManagedReference
    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
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
