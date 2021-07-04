package com.biclapp.service;

import com.biclapp.model.entity.Empresas;
import com.biclapp.repository.IEmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaServiceImpl implements IEmpresaService {

    @Autowired
    private IEmpresasRepository empresasRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Empresas> findAll() {
        return (List<Empresas>) empresasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empresas findById(Long id) throws Exception {
        return empresasRepository.findById(id).orElseThrow(
                () -> new Exception("La empresa con id ".concat(id.toString()).concat(" no existe."))
        );
    }
}
