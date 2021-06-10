package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreatePedidos;
import com.biclapp.model.DTO.DTODetallePedido;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdatePedidos;
import com.biclapp.model.entity.DetallesPedido;
import com.biclapp.model.entity.Pedidos;

import java.util.List;

public interface IPedidosService {

    List<Pedidos> findAll();

    List<Pedidos> findByIdUsuario(Long id_usuario);

    Pedidos findById(Long id) throws Exception;

    List<DetallesPedido> findByUserAndPedido(Long id_usuario, Long id_pedido) throws Exception;

    //List<Pedidos> findAllWithDetails(); // TODO: CREAR SP

    //Pedidos findByIdWithDetails(Long id_pedido); // TODO: CREAR SP

    //List<Pedidos> findByUserWithDetails(Long id_usuario); // TODO: CREAR SP

    //Pedidos findOneByUserWithDetails(Long id_pedido, Long id_usuario); // TODO: CREAR SP

    List<DTODetallePedido> findByUserAndPedidoWithDetail(Long id_usuario, Long id_pedido) throws Exception;

    void save(DTOCreatePedidos createPedidos) throws Exception;

    void createPedidoUser(DTOCreatePedidos createPedidos) throws Exception;

    void update(Long id, DTOUpdatePedidos updatePedidos) throws Exception;

    void updateEstado(Long id, DTOUpdate update) throws Exception;

    void delete(Long id) throws Exception;

}

