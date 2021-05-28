package com.biclapp.repository;

import com.biclapp.model.entity.Tokens;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITokensRepository extends CrudRepository<Tokens, Long> {

    Tokens findByEmail(String email);

    Optional<Tokens> findByEmailAndCodigo(String email, int codigo) throws Exception;
}
