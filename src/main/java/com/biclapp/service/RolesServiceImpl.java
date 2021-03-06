package com.biclapp.service;

import com.biclapp.model.entity.Roles;
import com.biclapp.repository.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolesServiceImpl implements IRolesService {

    @Autowired
    private IRolesRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Roles> findAll() {
        return (List<Roles>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Roles findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("El rol con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    @Transactional(readOnly = true)
    public Roles findByRol(String rol) {
        return repository.findByRol(rol);
    }

    @Override
    public void save(Roles rol) throws Exception {
        int contador = findAll().toArray().length;
        rol.setCodigo(contador + 1);
        repository.save(rol);
    }

    @Override
    public void update(Long id, Roles rol) throws Exception {
        Roles rolFound = findById(id);
        rolFound.setRol(rol.getRol());
        repository.save(rolFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
