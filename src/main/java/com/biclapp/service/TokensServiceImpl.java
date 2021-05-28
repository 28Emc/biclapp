package com.biclapp.service;

import com.biclapp.model.entity.Tokens;
import com.biclapp.repository.ITokensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokensServiceImpl implements ITokensService {

    @Autowired
    private ITokensRepository tokensRepository;

    @Override
    @Transactional(readOnly = true)
    public Tokens findByEmail(String email) {
        return tokensRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Tokens findByEmailAndCodigo(String email, int codigo) throws Exception {
        return tokensRepository.findByEmailAndCodigo(email, codigo).orElseThrow(() -> new Exception("El código no se encuenta o ya expiró."));
    }

    @Override
    public void save(Tokens token) {
        tokensRepository.save(token);
    }

    @Override
    public void delete(Long id) {
        tokensRepository.deleteById(id);
    }
}
