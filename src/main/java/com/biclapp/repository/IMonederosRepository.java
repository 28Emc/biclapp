package com.biclapp.repository;

import com.biclapp.model.entity.Monederos;
import org.springframework.data.repository.CrudRepository;

public interface IMonederosRepository extends CrudRepository<Monederos, Long> {

    Monederos findById_usuario(Long id_usuario);
}
