package com.biclapp.service;

import com.biclapp.model.entity.Membresias;

import java.util.List;

public interface IMembresiasService {

    List<Membresias> findAll();

    Membresias findById(Long id) throws Exception;

    void save(Membresias membresia) throws Exception;

    void update(Long id, Membresias membresia) throws Exception;

    void delete(Long id) throws Exception;

}
