package com.biclapp.service;

import com.biclapp.model.entity.Tokens;

public interface ITokensService {

    Tokens findByEmail(String email);

    Tokens findByEmailAndCodigo(String email, int codigo) throws Exception;

    void save(Tokens token);

    void delete(Long id);
}
