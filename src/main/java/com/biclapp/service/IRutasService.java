package com.biclapp.service;

import com.biclapp.model.Rutas;

import java.util.List;

public interface IRutasService {

    List<Rutas> findAll();

    Rutas findById(Long id) throws Exception;

    void save(Rutas ruta) throws Exception;

    void delete(Long id) throws Exception;

}
