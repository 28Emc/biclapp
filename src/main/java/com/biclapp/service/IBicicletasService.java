package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateBicicletas;
import com.biclapp.model.entity.Bicicletas;

import java.util.List;

public interface IBicicletasService {

    List<Bicicletas> findAll();

    Bicicletas findById(Long id) throws Exception;

    void save(Bicicletas bicicletas) throws Exception;

    void update(Long id, DTOUpdateBicicletas updateBicicleta) throws Exception;

    void updateEstado(Long id, DTOUpdate update) throws Exception;

    void delete(Long id) throws Exception;

}
