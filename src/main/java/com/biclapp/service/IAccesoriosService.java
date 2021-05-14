package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateAccesorios;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.entity.Accesorios;

import java.util.List;

public interface IAccesoriosService {

    List<Accesorios> findAll();

    Accesorios findById(Long id) throws Exception;

    void save(DTOCreateAccesorios createAccesorio) throws Exception;

    void update(Long id, DTOCreateAccesorios updateAccesorio) throws Exception;

    void updateEstado(Long id, DTOUpdate update) throws Exception;

    void delete(Long id) throws Exception;
}
