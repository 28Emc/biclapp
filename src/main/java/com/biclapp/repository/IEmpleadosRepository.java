package com.biclapp.repository;

import com.biclapp.model.entity.Empleados;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadosRepository extends CrudRepository<Empleados, Long> {
}
