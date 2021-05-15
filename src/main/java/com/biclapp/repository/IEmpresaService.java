package com.biclapp.repository;

import com.biclapp.model.entity.Empresas;

import java.util.List;

public interface IEmpresaService {

    List<Empresas> findAll();

    Empresas findById(Long id) throws Exception;
}
