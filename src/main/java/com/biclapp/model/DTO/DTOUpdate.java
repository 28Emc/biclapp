package com.biclapp.model.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DTOUpdate {

    @NotNull(message = "Id requerido")
    private Long id;

    @NotEmpty(message = "Estado requerido")
    private String estado;

    public DTOUpdate() {
    }

    public DTOUpdate(Long id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
