package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateUsuarios;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdatePassword;
import com.biclapp.model.DTO.DTOUpdateUsuarios;
import com.biclapp.model.entity.Usuarios;

import java.util.List;

public interface IUsuariosService {

    List<Usuarios> findAll();

    Usuarios findById(Long id) throws Exception;

    void save(DTOCreateUsuarios createUsuarios) throws Exception;

    void update(Long id, DTOUpdateUsuarios updateUsuario) throws Exception;

    void updateEstado(Long id, DTOUpdate update) throws Exception;

    void updatePasswordRequest(DTOUpdatePassword updatePassword) throws Exception;

    void delete(Long id) throws Exception;

}
