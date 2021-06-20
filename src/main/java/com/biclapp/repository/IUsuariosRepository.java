package com.biclapp.repository;

import com.biclapp.model.entity.Usuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuariosRepository extends CrudRepository<Usuarios, Long> {

    Optional<Usuarios> findByUsername(String username);

    Optional<Usuarios> findByUsernameOrNroDocumentoOrCelular(String username, String nroDocumento, String celular) throws Exception;

}
