package com.biclapp.repository;

import com.biclapp.model.Pedidos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPedidosRepository extends CrudRepository<Pedidos, Long> {
}
