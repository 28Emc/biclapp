package com.biclapp.service;

import com.biclapp.model.Bicicletas;
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
}
