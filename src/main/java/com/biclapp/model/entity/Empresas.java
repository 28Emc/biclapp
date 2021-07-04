package com.biclapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "empresas")
public class Empresas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Código requerido")
    private Integer codigo;

    @NotEmpty(message = "RUC requerido")
    @Size(min = 11, max = 11, message = "El RUC debe tener 11 caracteres")
    private String ruc;

    @NotEmpty(message = "Razón social requerida")
    @Size(min = 1, max = 100, message = "La razón social debe tener entre 1 y 100 caracteres")
    private String razon_social;

    @NotEmpty(message = "Dirección requerida")
    @Size(min = 1, max = 150, message = "La dirección debe tener entre 1 y 150 caracteres")
    private String direccion;

    @Size(max = 30, message = "EL nro. de teléfono debe tener como máximo 30 caracteres")
    private String telefono1;

    @Size(max = 30, message = "EL nro. de teléfono 2 debe tener como máximo 30 caracteres")
    private String telefono2;

    public Empresas() {
    }

    public Empresas(Long id, Integer codigo, String ruc, String razon_social, String direccion, String telefono1, String telefono2) {
        this.id = id;
        this.codigo = codigo;
        this.ruc = ruc;
        this.razon_social = razon_social;
        this.direccion = direccion;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }
}
