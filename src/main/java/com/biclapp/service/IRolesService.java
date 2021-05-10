package com.biclapp.service;

import com.biclapp.model.Roles;

import java.util.List;

public interface IRolesService {

    List<Roles> findAll();

    Roles findById(Long id) throws Exception;

    void save(Roles rol) throws Exception;

    void delete(Long id) throws Exception;
}
