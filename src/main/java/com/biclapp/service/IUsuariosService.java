package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateUsuarios;
import com.biclapp.model.entity.Usuarios;

import java.util.List;

public interface IUsuariosService {

    List<Usuarios> findAll();

    Usuarios findById(Long id) throws Exception;

    void save(DTOCreateUsuarios createUsuarios) throws Exception;

    void update(Long id, DTOCreateUsuarios createUsuarios) throws Exception;

    void delete(Long id) throws Exception;

}
