package com.biclapp.repository;

import com.biclapp.model.entity.Favoritos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFavoritosRepository extends CrudRepository<Favoritos, Long> {

    List<Favoritos> findById_usuario(Long id_usuario);
}
