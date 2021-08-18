package com.biclapp.model.DTO;

public class DTOCreateAveria {
    private int dniUsuario;
    private String direccion;
    private String motivo;

    public DTOCreateAveria() {
    }

    public DTOCreateAveria(int dniUsuario, String direccion, String motivo) {
        this.dniUsuario = dniUsuario;
        this.direccion = direccion;
        this.motivo = motivo;
    }

    public int getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(int dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
