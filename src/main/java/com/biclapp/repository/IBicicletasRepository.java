package com.biclapp.repository;

import com.biclapp.model.Bicicletas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBicicletasRepository extends CrudRepository<Bicicletas, Long> {
}
