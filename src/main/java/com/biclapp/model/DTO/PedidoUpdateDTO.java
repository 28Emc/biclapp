package com.biclapp.model.DTO;

public class PedidoUpdateDTO {
    private Long id;
    private String estado;

    public PedidoUpdateDTO() {
    }

    public PedidoUpdateDTO(Long id, String estado) {
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
