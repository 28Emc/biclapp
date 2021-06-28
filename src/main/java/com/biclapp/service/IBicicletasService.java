package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateBicicletas;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateBicicletas;
import com.biclapp.model.entity.Bicicletas;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IBicicletasService {

    List<Bicicletas> findAll();

    List<Bicicletas> findAllByEstado();

    Bicicletas findById(Long id) throws Exception;

    Optional<Bicicletas> findByMarcaAndModelo(String marca, String modelo) throws Exception;

    void save(Bicicletas bicicleta) throws Exception;

    void saveCustom(DTOCreateBicicletas createBicicleta) throws Exception;

    void update(Long id, DTOUpdateBicicletas updateBicicleta) throws Exception;

    void updateEstado(Long id, DTOUpdate update) throws Exception;

    void updatePhotoBicicleta(Long id, MultipartFile photo) throws Exception;

    void delete(Long id) throws Exception;

}
