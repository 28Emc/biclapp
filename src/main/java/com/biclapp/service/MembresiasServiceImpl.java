package com.biclapp.service;

import com.biclapp.model.Membresias;
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
}
