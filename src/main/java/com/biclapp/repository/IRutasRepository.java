package com.biclapp.repository;

import com.biclapp.model.entity.Rutas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRutasRepository extends CrudRepository<Rutas, Long> {
}
