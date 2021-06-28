package com.biclapp.repository;

import com.biclapp.model.entity.Averias;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAveriasRepository extends CrudRepository<Averias, Long> {
    List<Averias> findByIdUsuario(Long idUsuario);
}
