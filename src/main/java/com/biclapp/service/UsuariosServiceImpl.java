package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateUsuarios;
import com.biclapp.model.entity.Empleados;
import com.biclapp.model.entity.Membresias;
import com.biclapp.model.entity.Roles;
import com.biclapp.model.entity.Usuarios;
import com.biclapp.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuariosServiceImpl implements IUsuariosService {

    @Autowired
    private IUsuariosRepository repository;

    @Autowired
    private IRolesService rolesService;

    @Autowired
    private IMembresiasService membresiasService;

    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> findAll() {
        return (List<Usuarios>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuarios findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("El usuario con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    public void save(DTOCreateUsuarios createUsuarios) throws Exception {
        // TODO: POR MIENTRAS SE ESTÁ VALIDANDO EL USUARIO CON ESTADO "ACTIVO", SE DEBERÍA VALIDAR MEDIANTE
        // TODO: TOKEN/CELULAR/EMAIL.
        Roles rolFound = rolesService.findByRol("ROLE_USUARIO");
        Membresias membresiaFound = membresiasService.findById(createUsuarios.getId_membresia());
        Usuarios usuariosNew = new Usuarios(rolFound, membresiaFound.getId(), createUsuarios.getNombres(),
                createUsuarios.getApellidos(), createUsuarios.getNro_documento(), createUsuarios.getCelular(),
                createUsuarios.getDireccion(), createUsuarios.getUsername(), createUsuarios.getPassword(),
                createUsuarios.getEstado(), createUsuarios.getFoto(), true);
        repository.save(usuariosNew);
    }

    @Override
    public void update(Long id, DTOCreateUsuarios createUsuarios) throws Exception {
        Usuarios usuarioFound = findById(id);
        Membresias membresiaFound = membresiasService.findById(createUsuarios.getId_membresia());
        usuarioFound.setId_membresia(membresiaFound.getId());
        usuarioFound.setNombres(usuarioFound.getNombres());
        usuarioFound.setApellidos(usuarioFound.getApellidos());
        usuarioFound.setNro_documento(usuarioFound.getNro_documento());
        usuarioFound.setCelular(usuarioFound.getCelular());
        usuarioFound.setDireccion(usuarioFound.getDireccion());
        usuarioFound.setUsername(usuarioFound.getUsername());
        usuarioFound.setPassword(usuarioFound.getPassword());
        usuarioFound.setEstado(usuarioFound.getEstado());
        usuarioFound.setFoto(usuarioFound.getFoto());
        usuarioFound.setActivo(usuarioFound.isActivo());
        repository.save(usuarioFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
