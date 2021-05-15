package com.biclapp.repository;

import com.biclapp.model.entity.Empresas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresasRepository extends CrudRepository<Empresas, Long> {
}
