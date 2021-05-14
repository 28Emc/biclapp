package com.biclapp.repository;

import com.biclapp.model.entity.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesRepository extends CrudRepository<Roles, Long> {
}
