package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdateAccesorios;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.entity.Accesorios;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAccesoriosService {

    List<Accesorios> findAll();

    List<Accesorios> findAllByEstado();

    Accesorios findById(Long id) throws Exception;

    void save(Accesorios accesorio) throws Exception;

    void update(Long id, DTOUpdateAccesorios updateAccesorio) throws Exception;

    void updateEstado(Long id, DTOUpdate update) throws Exception;

    void updatePhotoAccesorio(Long id, MultipartFile photo) throws Exception;

    void delete(Long id) throws Exception;
}
