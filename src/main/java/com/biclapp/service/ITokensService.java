package com.biclapp.service;

import com.biclapp.model.entity.Tokens;

import java.util.List;

public interface ITokensService {

    List<Tokens> findByEmail(String email);

    Tokens findByEmailAndCodigo(String email, int codigo) throws Exception;

    void save(Tokens token);

    void delete(Long id);
}
