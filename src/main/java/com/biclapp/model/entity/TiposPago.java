package com.biclapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tipos_pago")
public class TiposPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 50, message = "El tipo de pago debe tener entre 1 y 50 caracteres")
    private String tipo;

    public TiposPago() {
    }

    public TiposPago(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
