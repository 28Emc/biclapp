package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateEmpleados;
import com.biclapp.model.entity.Empleados;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmpleadosService {

    List<Empleados> findAll();

    Empleados findById(Long id) throws Exception;

    void save(DTOCreateEmpleados createEmpleados) throws Exception;

    void update(Long id, DTOCreateEmpleados createEmpleados) throws Exception;

    void updatePhotoEmpleado(Long id, MultipartFile photo) throws Exception;

    void delete(Long id) throws Exception;

}
