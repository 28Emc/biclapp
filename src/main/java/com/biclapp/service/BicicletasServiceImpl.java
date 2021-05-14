package com.biclapp.service;

import com.biclapp.model.entity.Bicicletas;
import com.biclapp.repository.IBicicletasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BicicletasServiceImpl implements IBicicletasService {

    @Autowired
    private IBicicletasRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Bicicletas> findAll() {
        return (List<Bicicletas>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Bicicletas findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("La bicicleta con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    public void save(Bicicletas bicicleta) throws Exception {
        repository.save(bicicleta);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
