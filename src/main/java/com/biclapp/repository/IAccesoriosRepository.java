package com.biclapp.repository;

import com.biclapp.model.entity.Accesorios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccesoriosRepository extends CrudRepository<Accesorios, Long> {
}
