package com.biclapp.service;

import com.biclapp.model.entity.Membresias;
import com.biclapp.repository.IMembresiasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MembresiasServiceImpl implements IMembresiasService {

    @Autowired
    private IMembresiasRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Membresias> findAll() {
        return (List<Membresias>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Membresias findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("La membresía con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    public void save(Membresias membresia) throws Exception {
        int contador = findAll().toArray().length;
        membresia.setCodigo(contador + 1);
        repository.save(membresia);
    }

    @Override
    public void update(Long id, Membresias membresia) throws Exception {
        Membresias membresiaFound = findById(id);
        membresiaFound.setTipo(membresiaFound.getTipo());
        membresiaFound.setCuota(membresia.getCuota());
        repository.save(membresiaFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
