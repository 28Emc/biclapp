package com.biclapp.repository;

import com.biclapp.model.entity.Pedidos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPedidosRepository extends CrudRepository<Pedidos, Long> {

    //List<Pedidos> findAllWithDetails(); // TODO: CREAR SP

    //Pedidos findByIdWithDetails(Long id_pedido); // TODO: CREAR SP

    //List<Pedidos> findByUserWithDetails(Long id_usuario); // TODO: CREAR SP

    //Pedidos findOneByUserWithDetails(Long id_pedido, Long id_usuario); // TODO: CREAR SP
}
