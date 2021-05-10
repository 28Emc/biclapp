package com.biclapp.service;

import com.biclapp.model.Rutas;
import com.biclapp.repository.IRutasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RutasServiceImpl implements IRutasService {

    @Autowired
    private IRutasRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Rutas> findAll() {
        return (List<Rutas>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Rutas findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("La ruta con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    public void save(Rutas ruta) throws Exception {
        repository.save(ruta);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
