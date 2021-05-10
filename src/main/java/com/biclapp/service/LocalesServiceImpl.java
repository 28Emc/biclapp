package com.biclapp.service;

import com.biclapp.model.Locales;
import com.biclapp.repository.ILocalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocalesServiceImpl implements ILocalesService {

    @Autowired
    private ILocalesRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Locales> findAll() {
        return (List<Locales>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Locales findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("El local con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    public void save(Locales local) throws Exception {
        repository.save(local);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
