package com.biclapp.model.DTO;

public class BicicletaUpdateDTO {
    private Long id;
    private String estado;

    public BicicletaUpdateDTO() {
    }

    public BicicletaUpdateDTO(Long id, String estado) {
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
