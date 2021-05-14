package com.biclapp.repository;

import com.biclapp.model.entity.Locales;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocalesRepository extends CrudRepository<Locales, Long> {
}
