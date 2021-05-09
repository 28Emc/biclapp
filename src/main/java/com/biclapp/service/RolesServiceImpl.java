package com.biclapp.service;

import com.biclapp.model.Roles;
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
}
