package com.biclapp.service;

import com.biclapp.model.entity.Pedidos;

import java.util.List;

public interface IPedidosService {

    List<Pedidos> findAll();

    Pedidos findById(Long id) throws Exception;

    void save(Pedidos pedido) throws Exception;

    void delete(Long id) throws Exception;

}
