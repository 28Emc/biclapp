package com.biclapp.service;

import com.biclapp.model.entity.TiposPago;

import java.util.List;

public interface ITiposPagoService {

    List<TiposPago> findAll();

    TiposPago findById(Long id) throws Exception;

    void save(TiposPago tipoPago) throws Exception;

    void update(Long id, TiposPago tipoPago) throws Exception;

    void delete(Long id) throws Exception;

}
