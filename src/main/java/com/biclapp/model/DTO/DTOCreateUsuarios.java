package com.biclapp.model.DTO;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DTOCreateUsuarios {

    @NotNull(message = "Membresía requerida")
    private Long id_membresia;

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

    @NotEmpty(message = "Sexo requerido")
    private String sexo;

    private String peso;

    private String estatura;

    @NotEmpty(message = "Nombre de usuario requerido")
    @Size(min = 1, max = 50, message = "El nombre de usuario debe tener entre 1 y 50 caracteres")
    private String username;

    @NotEmpty(message = "Contraseña requerido")
    @Size(min = 1, max = 255, message = "La contraseña debe tener entre 1 y 255 caracteres")
    private String password;

    private String estado;

    private MultipartFile foto;

    public DTOCreateUsuarios() {
    }

    public DTOCreateUsuarios(Long id_membresia, String nombres, String apellidos, String nro_documento, String celular, String direccion, String sexo, String peso, String estatura, String username, String password, String estado, MultipartFile foto) {
        this.id_membresia = id_membresia;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nro_documento = nro_documento;
        this.celular = celular;
        this.direccion = direccion;
        this.sexo = sexo;
        this.peso = peso;
        this.estatura = estatura;
        this.username = username;
        this.password = password;
        this.estado = estado;
        this.foto = foto;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
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

    public MultipartFile getFoto() {
        return foto;
    }

    public void setFoto(MultipartFile foto) {
        this.foto = foto;
    }
}
