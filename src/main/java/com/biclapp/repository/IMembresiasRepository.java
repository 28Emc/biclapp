package com.biclapp.repository;

import com.biclapp.model.entity.Membresias;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMembresiasRepository extends CrudRepository<Membresias, Long> {
}
