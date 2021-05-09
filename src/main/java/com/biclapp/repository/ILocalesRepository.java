package com.biclapp.repository;

import com.biclapp.model.Locales;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocalesRepository extends CrudRepository<Locales, Long> {
}
