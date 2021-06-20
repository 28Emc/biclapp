package com.biclapp.service;

import com.biclapp.config.mail.EmailService;
import com.biclapp.model.DTO.*;
import com.biclapp.model.entity.*;
import com.biclapp.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private ITokensService tokenService;

    @Autowired
    private IMonederosService monederoService;

    @Autowired
    private EmailService emailService;

    @Value("${gcp.img-user-default}")
    private String rutaFoto;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${gcp.user-img-folder}")
    private String userImgfolder;

    @Value("${gcp.img-web-path}")
    private String imgWebPath;

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
    @Transactional(readOnly = true)
    public Optional<Usuarios> findByUsernameOrNroDocumentoOrCelular(String username, String nroDocumento, String celular) throws Exception {
        return repository.findByUsernameOrNroDocumentoOrCelular(username, nroDocumento, celular);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void save(DTOCreateUsuarios createUsuarios) throws Exception {
        Optional<Usuarios> usuarioFound = repository.findByUsernameOrNroDocumentoOrCelular(createUsuarios.getUsername(), createUsuarios.getNro_documento(), createUsuarios.getCelular());

        if (usuarioFound.isPresent()) {
            if (usuarioFound.get().getNroDocumento().trim().equals(createUsuarios.getNro_documento().trim())) {
                throw new Exception("¡El nro de documento ya está registrado en el sistema!");
            } else if (usuarioFound.get().getCelular().trim().equals(createUsuarios.getCelular().trim())) {
                throw new Exception("¡El número de celular ya está siendo utilizado por otra persona!");
            } else if (usuarioFound.get().getUsername().trim().equals(createUsuarios.getUsername().trim())) {
                throw new Exception("¡El nombre de usuario ya está siendo utilizado por otra persona!");
            }
        } else {
            Roles rolFound = rolesService.findByRol("ROLE_USUARIO");
            Membresias membresiaFound = membresiasService.findById(createUsuarios.getId_membresia());

            if (createUsuarios.getFoto() != null) {
                String namePhoto = createUsuarios.getUsername().split("@")[0].replace(" ", "-").toLowerCase();
                String path = userImgfolder.concat(namePhoto).concat(".jpg");
                rutaFoto = cloudStorageService.uploadImageToGCS(createUsuarios.getFoto(), path);
            }

            int contador = findAll().toArray().length;
            String encryptPassword = encoder.encode(createUsuarios.getPassword());
            Usuarios usuariosNew = new Usuarios(contador + 1, rolFound, membresiaFound.getId(), createUsuarios.getNombres(),
                    createUsuarios.getApellidos(), createUsuarios.getNro_documento(), createUsuarios.getCelular(),
                    createUsuarios.getDireccion(), createUsuarios.getSexo(), createUsuarios.getPeso(), createUsuarios.getEstatura(),
                    createUsuarios.getUsername(), encryptPassword, "B", rutaFoto, false);
            repository.save(usuariosNew);

            int codigoMonedero = monederoService.findAll().toArray().length;
            Monederos monedero = new Monederos(codigoMonedero + 1, usuariosNew.getId(), 1000);
            monederoService.save(monedero);

            DTOUpdateToken updateToken = new DTOUpdateToken(createUsuarios.getUsername(), null, null,
                    0, null);

            Map<String, Object> model = new HashMap<>();
            model.put("from", emailFrom);
            model.put("to", createUsuarios.getUsername());
            model.put("subject", "Biclapp - Verificación cuenta");
            model.put("titulo-cabecera", "Verificación cuenta");

            int codigoRandom = (int) Math.floor(Math.random() * (1 - 9999 + 1) + 9999);
            String codStr = String.valueOf(codigoRandom);

            while (codStr.length() < 4) {
                codStr = codStr.concat("0");
            }

            codigoRandom = Integer.parseInt(codStr);
            Tokens activateToken = new Tokens(updateToken.getEmail(), "ACT-1", null,
                    codigoRandom, LocalDateTime.now());
            tokenService.save(activateToken);

            model.put("codigo-verificacion", activateToken.getCodigo());

            emailService.enviarEmail(model, "VALIDAR CUENTA");
        }
    }

    @Override
    @Transactional
    public void activateUserRequest(DTOUpdateToken updateToken) throws Exception {
        int codigoRandom = (int) Math.floor(Math.random() * (1 - 9999 + 1) + 9999);
        String codStr = String.valueOf(codigoRandom);

        while (codStr.length() < 4) {
            codStr = codStr.concat("0");
        }

        codigoRandom = Integer.parseInt(codStr);
        Tokens activateToken = new Tokens(updateToken.getEmail(), "ACT-1", null,
                codigoRandom, LocalDateTime.now());
        tokenService.save(activateToken);

        Map<String, Object> model = new HashMap<>();
        model.put("from", emailFrom);
        model.put("to", activateToken.getEmail());
        model.put("subject", "Biclapp - Solicitud de recuperación de contraseña");
        model.put("titulo-cabecera", "Recuperar Cuenta");
        model.put("codigo-verificacion", activateToken.getCodigo());
        emailService.enviarEmail(model, "CAMBIO CONTRASEÑA");
    }

    @Override
    @Transactional
    public void activateUserAction(DTOUpdateToken updateToken) throws Exception {
        // RECIBIR CODIGO DE 4 DÍGITOS, BUSCAR TOKEN POR CODIGO Y EMAIL Y ACTIVAR USUARIO
        Tokens tokenFound = tokenService.findByEmailAndCodigo(updateToken.getEmail(), updateToken.getCodigo());
        Usuarios usuarioFound = repository.findByUsername(updateToken.getEmail()).orElseThrow(() -> new Exception("¡El usuario no existe!"));
        usuarioFound.setEstado("A");
        usuarioFound.setActivo(true);
        tokenService.delete(tokenFound.getId());
        repository.save(usuarioFound);
        Monederos monederoFound = monederoService.findByUser(usuarioFound.getId());
        if (monederoFound.getPuntos() == 1000) {
            int puntos = monederoFound.getPuntos() + 1000;
            monederoService.editPuntos(monederoFound.getId(), new DTOUpdateMonederos(usuarioFound.getId(), puntos));
        }

        Map<String, Object> model = new HashMap<>();
        model.put("from", emailFrom);
        model.put("to", usuarioFound.getUsername());
        model.put("subject", "Biclapp - ¡Cuenta verificada!");
        model.put("titulo-cabecera", "¡Cuenta verificada!");

        emailService.enviarEmail(model, "CONFIRMA VALIDACIÓN CUENTA");
    }

    @Override
    @Transactional
    public void update(Long id, DTOUpdateUsuarios updateUsuario) throws Exception {
        Usuarios usuarioFound = findById(id);
        Membresias membresiaFound = membresiasService.findById(updateUsuario.getId_membresia());
        usuarioFound.setId_membresia(membresiaFound.getId());
        usuarioFound.setNombres(updateUsuario.getNombres());
        usuarioFound.setApellidos(updateUsuario.getApellidos());
        usuarioFound.setNroDocumento(updateUsuario.getNro_documento());
        usuarioFound.setCelular(updateUsuario.getCelular());
        usuarioFound.setDireccion(updateUsuario.getDireccion());
        usuarioFound.setSexo(updateUsuario.getSexo());
        usuarioFound.setPeso(updateUsuario.getPeso());
        usuarioFound.setEstatura(updateUsuario.getEstatura());
        usuarioFound.setUsername(updateUsuario.getUsername());
        usuarioFound.setPassword(usuarioFound.getPassword());
        usuarioFound.setEstado(usuarioFound.getEstado());
        usuarioFound.setFoto(usuarioFound.getFoto());
        usuarioFound.setActivo(usuarioFound.isActivo());
        repository.save(usuarioFound);
    }

    @Override
    @Transactional
    public void updateStatus(Long id, DTOUpdate update) throws Exception {
        Usuarios usuarioFound = findById(id);
        usuarioFound.setActivo(update.getEstado().equals("A"));
        usuarioFound.setEstado(update.getEstado());
        repository.save(usuarioFound);
    }

    @Override
    @Transactional
    public void updateMembresia(Long id, Long id_membresia) throws Exception {
        Usuarios usuarioFound = findById(id);
        usuarioFound.setId_membresia(id_membresia);
        repository.save(usuarioFound);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
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
            String codStr = String.valueOf(codigoRandom);

            while (codStr.length() < 4) {
                codStr = codStr.concat("0");
            }

            codigoRandom = Integer.parseInt(codStr);
            updateToken.setCodigo(codigoRandom);
            Tokens token = new Tokens(updateToken.getEmail(), updateToken.getTipo_accion(), null,
                    codigoRandom, LocalDateTime.now());
            List<Tokens> tokensFound = tokenService.findByEmail(updateToken.getEmail());
            if (tokensFound.size() > 0) {
                tokensFound.forEach(tokenItem -> tokenService.delete(tokenItem.getId()));
            }
            tokenService.save(token);

            Map<String, Object> model = new HashMap<>();
            model.put("from", emailFrom);
            model.put("to", usuarioFound.get().getUsername());
            model.put("subject", "Biclapp - Solicitud de cambio de contraseña");
            model.put("titulo-cabecera", "Cambio de contraseña");
            model.put("codigo-verificacion", token.getCodigo());

            emailService.enviarEmail(model, "CAMBIO CONTRASEÑA");
        }
    }

    @Override
    @Transactional
    public boolean validateCode(DTOUpdateToken updateToken) throws Exception {
        // RECIBO EL CODIGO DE 4 DÍGITOS, BUSCO EL USUARIO POR EL CODIGO Y EL CORREO,
        // SI ENCUENTRO EL CÓDIGO ASOCIADO, MANDO TRUE COMO CONFIRMACIÓN
        Optional<Tokens> tokenFound = Optional.ofNullable(tokenService.findByEmailAndCodigo(updateToken.getEmail(), updateToken.getCodigo()));
        tokenFound.ifPresent(tokens -> tokenService.delete(tokens.getId()));
        return true;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updatePasswordAction(DTOUpdateToken updateToken) throws Exception {
        // BUSCO EL USUARIO POR EL CORREO, ACTUALIZO EL USUARIO Y REENVÍO UN CORREO DE CONFIRMACIÓN.
        Usuarios usuarioFound = repository.findByUsername(updateToken.getEmail()).orElseThrow(() -> new Exception("¡El usuario no existe!"));
        usuarioFound.setPassword(encoder.encode(updateToken.getPassword()));
        repository.save(usuarioFound);

        Map<String, Object> model = new HashMap<>();
        model.put("from", emailFrom);
        model.put("to", usuarioFound.getUsername());
        model.put("subject", "Biclapp - ¡Contraseña actualizada!");
        model.put("titulo-cabecera", "¡Contraseña actualizada!");

        emailService.enviarEmail(model, "CONFIRMA ACTUALIZACIÓN CONTRASEÑA");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updatePhotoUser(Long id, MultipartFile photo) throws Exception {
        Usuarios usuarioFound = findById(id);
        String namePhoto = usuarioFound.getUsername().split("@")[0].replace(" ", "-").toLowerCase();
        String imgPathUser = usuarioFound.getFoto().substring(usuarioFound.getFoto().indexOf(userImgfolder));

        //cloudStorageService.deleteImageFromGCS(imgPathUser);

        String path = userImgfolder.concat(namePhoto).concat(".jpg");
        rutaFoto = cloudStorageService.uploadImageToGCS(photo, path);
        usuarioFound.setFoto(rutaFoto);
        repository.save(usuarioFound);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
