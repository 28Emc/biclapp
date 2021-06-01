package com.biclapp.repository;

import com.biclapp.model.entity.Bicicletas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBicicletasRepository extends CrudRepository<Bicicletas, Long> {

    Optional<Bicicletas> findByMarcaAndModelo(String marca, String modelo);

}
