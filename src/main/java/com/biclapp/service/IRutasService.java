package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdateRutas;
import com.biclapp.model.entity.Rutas;

import java.util.List;

public interface IRutasService {

    List<Rutas> findAll();

    List<Rutas> findById_usuario(Long id_usuario);

    Rutas findById(Long id) throws Exception;

    void save(Rutas ruta) throws Exception;

    void update(Long id, DTOUpdateRutas updateRuta) throws Exception;

    void delete(Long id) throws Exception;

}
