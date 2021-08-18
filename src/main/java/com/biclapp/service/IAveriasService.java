package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateAveria;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateAverias;
import com.biclapp.model.entity.Averias;

import java.util.List;

public interface IAveriasService {

    List<Averias> findAll();

    Averias findById(Long id) throws Exception;

    List<Averias> findByIdUsuario(Long idUsuario) throws Exception;

    void save(Averias averia) throws Exception;

    void saveAdmin(DTOCreateAveria dtoCreateAveria) throws Exception;

    void update(Long id, DTOUpdateAverias updateAveria) throws Exception;

    void updateEstado(Long id, DTOUpdate update) throws Exception;

    void delete(Long id) throws Exception;
}
