package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdateMonederos;
import com.biclapp.model.entity.Monederos;

import java.util.List;

public interface IMonederosService {

    List<Monederos> findAll();

    Monederos findById(Long id) throws Exception;

    Monederos findByUser(Long id_usuario) throws Exception;

    void save(Monederos monedero) throws Exception;

    void editPuntos(Long id, DTOUpdateMonederos updateMonedero) throws Exception;

    void delete(Long id) throws Exception;
}
