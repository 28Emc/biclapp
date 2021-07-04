package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateAccesorios;
import com.biclapp.model.DTO.DTOUpdateFavoritos;
import com.biclapp.model.entity.Accesorios;
import com.biclapp.model.entity.Favoritos;

import java.util.List;

public interface IFavoritosService {

    List<Favoritos> findAll();

    List<Favoritos> findByUser(Long id_usuario) throws Exception;

    Favoritos findById(Long id) throws Exception;

    void save(Favoritos favorito) throws Exception;

    void update(Long id, DTOUpdateFavoritos updateFavorito) throws Exception;

    void delete(Long id) throws Exception;
}
