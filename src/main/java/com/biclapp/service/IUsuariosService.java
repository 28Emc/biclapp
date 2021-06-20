package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateUsuarios;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateToken;
import com.biclapp.model.DTO.DTOUpdateUsuarios;
import com.biclapp.model.entity.Usuarios;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IUsuariosService {

    List<Usuarios> findAll();

    Usuarios findById(Long id) throws Exception;

    Usuarios findByUsername(String username) throws Exception;

    Optional<Usuarios> findByUsernameOrNroDocumentoOrCelular(String username, String nroDocumento, String celular) throws Exception;

    void save(DTOCreateUsuarios createUsuarios) throws Exception;

    void activateUserRequest(DTOUpdateToken updateToken) throws Exception;

    void activateUserAction(DTOUpdateToken updateToken) throws Exception;

    void update(Long id, DTOUpdateUsuarios updateUsuario) throws Exception;

    void updateStatus(Long id, DTOUpdate update) throws Exception;

    void updateMembresia(Long id, Long id_membresia) throws Exception;

    void updatePasswordRequest(DTOUpdateToken updatePassword) throws Exception;

    boolean validateCode(DTOUpdateToken updateToken) throws Exception;

    void updatePasswordAction(DTOUpdateToken updatePassword) throws Exception;

    void updatePhotoUser(Long id, MultipartFile photo) throws Exception;

    void delete(Long id) throws Exception;

}
