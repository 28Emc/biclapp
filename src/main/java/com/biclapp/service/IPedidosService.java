package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreatePedidos;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdatePedidos;
import com.biclapp.model.entity.Pedidos;

import java.util.List;

public interface IPedidosService {

    List<Pedidos> findAll();

    Pedidos findById(Long id) throws Exception;

    List<Pedidos> findAllWithDetails(); // TODO: CREAR SP

    Pedidos findByIdWithDetails(Long id_pedido); // TODO: CREAR SP

    List<Pedidos> findByUserWithDetails(Long id_usuario); // TODO: CREAR SP

    Pedidos findOneByUserWithDetails(Long id_pedido, Long id_usuario); // TODO: CREAR SP

    void save(DTOCreatePedidos createPedidos) throws Exception;

    void update(Long id, DTOUpdatePedidos updatePedidos) throws Exception;

    void updateEstado(Long id, DTOUpdate update) throws Exception;

    void delete(Long id) throws Exception;

}

