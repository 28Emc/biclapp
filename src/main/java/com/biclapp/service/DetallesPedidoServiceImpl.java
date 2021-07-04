package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdateDetallesPedido;
import com.biclapp.model.entity.DetallesPedido;
import com.biclapp.repository.IDetallesPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DetallesPedidoServiceImpl implements IDetallesPedidoService {

    @Autowired
    private IDetallesPedidoRepository detallesPedidoRepository;

    /*
    @Override
    @Transactional(readOnly = true)
    public List<DetallesPedido> findById_Pedido(Long id_pedido) {
        return detallesPedidoRepository.findById_Pedido(id_pedido);
    }
    */

    @Override
    @Transactional(readOnly = true)
    public DetallesPedido findById(Long id) throws Exception {
        return detallesPedidoRepository.findById(id).orElseThrow(
                () -> new Exception("El detalle pedido con id ".concat(id.toString()).concat(" no existe."))
        );
    }

    @Override
    public void save(DetallesPedido detallePedido) throws Exception {
        detallesPedidoRepository.save(detallePedido);
    }

    @Override
    public void update(Long id, DTOUpdateDetallesPedido updateDetallesPedido) throws Exception {
        DetallesPedido detallePedidoFound = findById(id);
        detallePedidoFound.setCantidad(updateDetallesPedido.getCantidad());
        detallePedidoFound.setPrecio(updateDetallesPedido.getPrecio());
        detallePedidoFound.setTotal(updateDetallesPedido.getTotal());
        detallesPedidoRepository.save(detallePedidoFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        detallesPedidoRepository.deleteById(id);
    }
}
