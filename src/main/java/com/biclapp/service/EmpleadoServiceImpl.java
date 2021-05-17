package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateEmpleados;
import com.biclapp.model.entity.Empleados;
import com.biclapp.model.entity.Roles;
import com.biclapp.repository.IEmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements IEmpleadosService {

    @Autowired
    private IEmpleadosRepository empleadosRepository;

    @Autowired
    private IRolesService rolesService;

    @Override
    @Transactional(readOnly = true)
    public List<Empleados> findAll() {
        return (List<Empleados>) empleadosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empleados findById(Long id) throws Exception {
        return empleadosRepository.findById(id).orElseThrow(() -> new Exception("El empleado con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    public void save(DTOCreateEmpleados createEmpleados) throws Exception {
        Roles rolFound = rolesService.findByRol("ROLE_EMPLEADO");
        int contador = findAll().toArray().length;
        Empleados empleadosNew = new Empleados(contador + 1, rolFound, createEmpleados.getNombres(),
                createEmpleados.getApellidos(), createEmpleados.getNro_documento(), createEmpleados.getCelular(),
                createEmpleados.getDireccion(), createEmpleados.getUsername(), createEmpleados.getPassword(),
                createEmpleados.getEstado(), createEmpleados.getFoto(), true);
        empleadosRepository.save(empleadosNew);
    }

    @Override
    public void update(Long id, DTOCreateEmpleados createEmpleados) throws Exception {
        Empleados empleadoFound = findById(id);
        empleadoFound.setCodigo(createEmpleados.getCodigo());
        empleadoFound.setNombres(createEmpleados.getNombres());
        empleadoFound.setApellidos(createEmpleados.getApellidos());
        empleadoFound.setNro_documento(createEmpleados.getNro_documento());
        empleadoFound.setCelular(createEmpleados.getCelular());
        empleadoFound.setDireccion(createEmpleados.getDireccion());
        empleadoFound.setUsername(createEmpleados.getUsername());
        empleadoFound.setPassword(createEmpleados.getPassword());
        empleadoFound.setEstado(createEmpleados.getEstado());
        empleadoFound.setFoto(createEmpleados.getFoto());
        empleadoFound.setActivo(true);
        empleadosRepository.save(empleadoFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        empleadosRepository.deleteById(id);
    }
}
