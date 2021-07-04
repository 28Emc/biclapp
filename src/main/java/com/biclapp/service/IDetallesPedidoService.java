package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateAccesorios;
import com.biclapp.model.DTO.DTOUpdateDetallesPedido;
import com.biclapp.model.entity.Accesorios;
import com.biclapp.model.entity.DetallesPedido;

import java.util.List;

public interface IDetallesPedidoService {

    //List<DetallesPedido> findById_Pedido(Long id_pedido); // TODO: CREAR SP

    DetallesPedido findById(Long id) throws Exception;

    void save(DetallesPedido detallePedido) throws Exception;

    void update(Long id, DTOUpdateDetallesPedido updateDetallePedido) throws Exception;

    void delete(Long id) throws Exception;
}
