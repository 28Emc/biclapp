package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateRecorridos;
import com.biclapp.model.entity.Recorridos;

import java.util.List;

public interface IRecorridosService {

    List<Recorridos> findAll();

    List<Recorridos> findByUser(Long id_usuario);

    Recorridos findById(Long id) throws Exception;

    Recorridos findByIdAndUser(Long id, Long id_usuario);

    void save(Recorridos recorrido) throws Exception;

    void update(Long id, DTOUpdateRecorridos updateRecorrido) throws Exception;

    void updateEstado(Long id, DTOUpdate update) throws Exception;

    void delete(Long id) throws Exception;

}
