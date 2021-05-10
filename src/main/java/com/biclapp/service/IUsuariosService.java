package com.biclapp.service;

import com.biclapp.model.Usuarios;

import java.util.List;

public interface IUsuariosService {

    List<Usuarios> findAll();

    Usuarios findById(Long id) throws Exception;

    void save(Usuarios usuario) throws Exception;

    void delete(Long id) throws Exception;

}
