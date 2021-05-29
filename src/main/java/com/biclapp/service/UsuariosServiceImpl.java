package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateUsuarios;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateToken;
import com.biclapp.model.DTO.DTOUpdateUsuarios;
import com.biclapp.model.entity.*;
import com.biclapp.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ITokensService tokenService;

    @Autowired
    private IMonederosService monederoService;

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
        Optional<Usuarios> usuarioFound = repository.findByUsername(createUsuarios.getUsername());
        if (usuarioFound.isPresent()) {
            throw new Exception("¡El usuario ya se encuentra registrado!");
        } else {
            Roles rolFound = rolesService.findByRol("ROLE_USUARIO");
            Membresias membresiaFound = membresiasService.findById(createUsuarios.getId_membresia());
            if (createUsuarios.getFoto() != null) {
                rutaFoto = cloudStorageService.uploadImageToGCS(createUsuarios.getFoto(), createUsuarios.getUsername());
            }
            int contador = findAll().toArray().length;
            String encryptPassword = encoder.encode(createUsuarios.getPassword());
            Usuarios usuariosNew = new Usuarios(contador + 1, rolFound, membresiaFound.getId(), createUsuarios.getNombres(),
                    createUsuarios.getApellidos(), createUsuarios.getNro_documento(), createUsuarios.getCelular(),
                    createUsuarios.getDireccion(), createUsuarios.getUsername(), encryptPassword,
                    "B", rutaFoto, false);
            repository.save(usuariosNew);

            int codigoMonedero = monederoService.findAll().toArray().length;
            Monederos monedero = new Monederos(codigoMonedero, usuariosNew.getId(), 0);
            monederoService.save(monedero);

            DTOUpdateToken updateToken = new DTOUpdateToken(createUsuarios.getUsername(), null, null,
                    0, null);
            activateUserRequest(updateToken);
        }
    }

    @Override
    public void activateUserRequest(DTOUpdateToken updateToken) throws Exception {
        int codigoRandom = (int) Math.floor(Math.random() * (1 - 9999 + 1) + 9999);
        Tokens activateToken = new Tokens(updateToken.getEmail(), "ACT-1", null,
                codigoRandom, LocalDateTime.now());
        tokenService.save(activateToken);
        // TODO: ENVIAR UN CORREO DE VERIFICACIÓN DE EMAIL (email, codigoRandom)
    }

    @Override
    public void activateUserAction(DTOUpdateToken updateToken) throws Exception {
        // RECIBIR CODIGO DE 4 DÍGITOS, BUSCAR TOKEN POR CODIGO Y EMAIL Y ACTIVAR USUARIO
        Tokens tokenFound = tokenService.findByEmailAndCodigo(updateToken.getEmail(), updateToken.getCodigo());
        Usuarios usuarioFound = repository.findByUsername(updateToken.getEmail()).orElseThrow(() -> new Exception("¡El usuario no existe!"));
        usuarioFound.setEstado("A");
        usuarioFound.setActivo(true);
        tokenService.delete(tokenFound.getId());
        repository.save(usuarioFound);
        // TODO: SERVICE DE ENVÍO DE CORREO DE CONFIRMACIÓN ACTIVACIÓN CUENTA (email)
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
    public void updateStatus(Long id, DTOUpdate update) throws Exception {
        Usuarios usuarioFound = findById(id);
        usuarioFound.setActivo(update.getEstado().equals("A"));
        usuarioFound.setEstado(update.getEstado());
        repository.save(usuarioFound);
    }

    @Override
    public void updatePasswordRequest(DTOUpdateToken updateToken) throws Exception {
        // RECIBO EMAIL DEL USUARIO Y VERIFICAR SI EL USUARIO CON ESE CORREO ESTÁ REGISTRADO Y DESACTIVADO
        // SI ES ASÍ, GENERO UN CÓDIGO DE 4 DÍGITOS Y LO ENVÍO AL CORREO RECIBIDO.
        Optional<Usuarios> usuarioFound = repository.findByUsername(updateToken.getEmail());
        if (usuarioFound.isEmpty()) {
            throw new Exception("¡El usuario no se encuentra registrado!");
        } else if (!usuarioFound.get().isActivo()) {
            throw new Exception("¡El usuario se encuentra inactivo! Activar la cuenta para continuar.");
        } else {
            int codigoRandom = (int) Math.floor(Math.random() * (1 - 9999 + 1) + 9999);
            updateToken.setCodigo(codigoRandom);
            Tokens token = new Tokens(updateToken.getEmail(), updateToken.getTipo_accion(), null,
                    codigoRandom, LocalDateTime.now());
            tokenService.save(token);
            // TODO: SERVICE DE ENVÍO DE CORREO DE CÓDIGO DE VALIDACIÓN (email, codigoRandom)
        }
    }

    @Override
    public void updatePasswordAction(DTOUpdateToken updateToken) throws Exception {
        // RECIBO EL CODIGO DE 4 DÍGITOS Y LA CONTRASEÑA, BUSCO EL USUARIO POR EL CODIGO Y EL CORREO,
        // ACTUALIZO EL USUARIO Y REENVÍO UN CORREO DE CONFIRMACIÓN.
        Tokens tokenFound = tokenService.findByEmailAndCodigo(updateToken.getEmail(), updateToken.getCodigo());
        Usuarios usuarioFound = repository.findByUsername(updateToken.getEmail()).orElseThrow(() -> new Exception("¡El usuario no existe!"));
        usuarioFound.setPassword(encoder.encode(updateToken.getPassword()));
        repository.save(usuarioFound);
        tokenService.delete(tokenFound.getId());
        // TODO: SERVICE DE ENVÍO DE CORREO DE CONFIRMACIÓN ACTUALIZACIÓN CONTRASEÑA (email)
    }

    @Override
    public void updatePhotoUser(Long id, MultipartFile photo) throws Exception {
        Usuarios usuarioFound = findById(id);
        rutaFoto = cloudStorageService.uploadImageToGCS(photo, usuarioFound.getUsername().split("@")[0]);
        usuarioFound.setFoto(rutaFoto);
        repository.save(usuarioFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
