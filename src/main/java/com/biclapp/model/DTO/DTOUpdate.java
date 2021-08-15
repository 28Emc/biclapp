package com.biclapp.model.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class DTOUpdate {

    @NotNull(message = "Id requerido")
    private Long id;

    @NotEmpty(message = "Estado requerido")
    private String estado;

    private LocalDateTime fecha;

    public DTOUpdate() {
    }

    public DTOUpdate(Long id, String estado, LocalDateTime fecha) {
        this.id = id;
        this.estado = estado;
        this.fecha = fecha;
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
