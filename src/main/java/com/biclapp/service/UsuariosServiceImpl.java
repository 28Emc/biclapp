package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateUsuarios;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.entity.Empleados;
import com.biclapp.model.entity.Membresias;
import com.biclapp.model.entity.Roles;
import com.biclapp.model.entity.Usuarios;
import com.biclapp.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UsuariosServiceImpl implements IUsuariosService {

    @Autowired
    private IUsuariosRepository repository;

    @Autowired
    private IRolesService rolesService;

    @Autowired
    private IMembresiasService membresiasService;

    @Autowired
    private GoogleCloudStorageService cloudStorageService;

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
        int contador = findAll().toArray().length;
        String rutaFoto = cloudStorageService.uploadImageToGCS(createUsuarios.getFoto(), createUsuarios.getUsername());
        Usuarios
                usuariosNew = new Usuarios(contador + 1, rolFound, membresiaFound.getId(), createUsuarios.getNombres(),
                createUsuarios.getApellidos(), createUsuarios.getNro_documento(), createUsuarios.getCelular(),
                createUsuarios.getDireccion(), createUsuarios.getUsername(), createUsuarios.getPassword(),
                "A", rutaFoto, true);
        // TODO: SE DEBERÍA CREAR UN MONEDERO AL CREAR EL USUARIO, O SE CREA A PARTE ?
        repository.save(usuariosNew);
    }

    @Override
    public void update(Long id, DTOCreateUsuarios createUsuarios) throws Exception {
        Usuarios usuarioFound = findById(id);
        Membresias membresiaFound = membresiasService.findById(createUsuarios.getId_membresia());
        usuarioFound.setId_membresia(membresiaFound.getId());
        usuarioFound.setNombres(createUsuarios.getNombres());
        usuarioFound.setApellidos(createUsuarios.getApellidos());
        usuarioFound.setNro_documento(createUsuarios.getNro_documento());
        usuarioFound.setCelular(createUsuarios.getCelular());
        usuarioFound.setDireccion(createUsuarios.getDireccion());
        usuarioFound.setUsername(createUsuarios.getUsername());
        usuarioFound.setPassword(createUsuarios.getPassword());
        usuarioFound.setEstado(createUsuarios.getEstado());
        String rutaFoto = cloudStorageService.uploadImageToGCS(createUsuarios.getFoto(), createUsuarios.getUsername());
        usuarioFound.setFoto(rutaFoto);
        usuarioFound.setActivo(true);
        repository.save(usuarioFound);
    }

    @Override
    public void updateEstado(Long id, DTOUpdate update) throws Exception {
        Usuarios usuarioFound = findById(id);
        usuarioFound.setActivo(update.getEstado().equals("A"));
        usuarioFound.setEstado(update.getEstado());
        repository.save(usuarioFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
