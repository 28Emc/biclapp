package com.biclapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "locales")
public class Locales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codigo;

    @NotNull(message = "Empresa requerida")
    @Column(name = "id_empresa")
    private Long idEmpresa;

    @NotEmpty(message = "Dirección requerida")
    @Size(min = 1, max = 100, message = "La dirección debe tener entre 1 y 100 caracteres")
    private String direccion;

    @Size(max = 9, message = "EL nro. de teléfono debe tener como máximo 9 caracteres")
    private String telefono1;

    @Size(max = 9, message = "EL nro. de teléfono 2 debe tener como máximo 9 caracteres")
    private String telefono2;

    public Locales() {
    }

    public Locales(Long id, Integer codigo, Long idEmpresa, String direccion, String telefono1, String telefono2) {
        this.id = id;
        this.codigo = codigo;
        this.idEmpresa = idEmpresa;
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

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
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
