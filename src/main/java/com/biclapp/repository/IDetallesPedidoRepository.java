package com.biclapp.repository;

import com.biclapp.model.entity.DetallesPedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetallesPedidoRepository extends CrudRepository<DetallesPedido, Long> {
    List<DetallesPedido> findById_Pedido(Long id_pedido);
}
