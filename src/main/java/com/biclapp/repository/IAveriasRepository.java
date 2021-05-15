package com.biclapp.repository;

import com.biclapp.model.entity.Averias;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAveriasRepository extends CrudRepository<Averias, Long> {
}
