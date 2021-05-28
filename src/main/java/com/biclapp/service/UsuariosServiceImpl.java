package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateUsuarios;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdatePassword;
import com.biclapp.model.DTO.DTOUpdateUsuarios;
import com.biclapp.model.entity.Empleados;
import com.biclapp.model.entity.Membresias;
import com.biclapp.model.entity.Roles;
import com.biclapp.model.entity.Usuarios;
import com.biclapp.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${gcp.img-user-default}")
    private String rutaFoto;

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
    @Transactional(readOnly = true)
    public Usuarios findByUsername(String username) throws Exception {
        return repository.findByUsername(username).orElseThrow(() -> new Exception("Nombre de usuario y/o contraseña incorrectos."));
    }

    @Override
    public void save(DTOCreateUsuarios createUsuarios) throws Exception {
        // TODO: POR MIENTRAS SE ESTÁ VALIDANDO EL USUARIO CON ESTADO "ACTIVO", SE DEBERÍA VALIDAR MEDIANTE
        // TODO: TOKEN/CELULAR/EMAIL.
        Roles rolFound = rolesService.findByRol("ROLE_USUARIO");
        Membresias membresiaFound = membresiasService.findById(createUsuarios.getId_membresia());
        int contador = findAll().toArray().length;

        if (createUsuarios.getFoto() != null) {
            rutaFoto = cloudStorageService.uploadImageToGCS(createUsuarios.getFoto(), createUsuarios.getUsername());
        }

        // TODO: LÓGICA DE ENCRIPTACIÓN DE CONTRASEÑA
        String encryptPassword = createUsuarios.getPassword();

        Usuarios usuariosNew = new Usuarios(contador + 1, rolFound, membresiaFound.getId(), createUsuarios.getNombres(),
                createUsuarios.getApellidos(), createUsuarios.getNro_documento(), createUsuarios.getCelular(),
                createUsuarios.getDireccion(), createUsuarios.getUsername(), encryptPassword,
                "A", rutaFoto, true);
        // TODO: SE DEBERÍA CREAR UN MONEDERO AL CREAR EL USUARIO, O SE CREA A PARTE ?
        repository.save(usuariosNew);
    }

    @Override
    public void update(Long id, DTOUpdateUsuarios updateUsuario) throws Exception {
        Usuarios usuarioFound = findById(id);
        Membresias membresiaFound = membresiasService.findById(updateUsuario.getId_membresia());
        usuarioFound.setId_membresia(membresiaFound.getId());
        usuarioFound.setNombres(updateUsuario.getNombres());
        usuarioFound.setApellidos(updateUsuario.getApellidos());
        usuarioFound.setNro_documento(updateUsuario.getNro_documento());
        usuarioFound.setCelular(updateUsuario.getCelular());
        usuarioFound.setDireccion(updateUsuario.getDireccion());
        usuarioFound.setUsername(updateUsuario.getUsername());
        usuarioFound.setPassword(usuarioFound.getPassword());
        usuarioFound.setEstado(usuarioFound.getEstado());
        usuarioFound.setFoto(usuarioFound.getFoto());
        usuarioFound.setActivo(usuarioFound.isActivo());
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
    public void updatePasswordRequest(DTOUpdatePassword updatePassword) throws Exception {
        // TODO: VALIDAR SI ACCEDO A ESTE MÉTODO DESDE DENTRO O FUERA DEL SISTEMA
        // TODO: RECIBO EMAIL DEL USUARIO Y VERIFICAR SI EL USUARIO CON ESE CORREO ESTÁ REGISTRADO Y DESACTIVADO
        // TODO: SI ES ASÍ, GENERO UN CÓDIGO DE 4 DÍGITOS Y LO ENVÍO AL CORREO RECIBIDO.
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
