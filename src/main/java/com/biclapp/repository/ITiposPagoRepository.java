package com.biclapp.repository;

import com.biclapp.model.entity.TiposPago;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITiposPagoRepository extends CrudRepository<TiposPago, Long> {
}
