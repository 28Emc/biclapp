package com.biclapp.service;

import com.biclapp.model.entity.Locales;

import java.util.List;

public interface ILocalesService {

    List<Locales> findAll();

    List<Locales> findByEmpresa(Long id_empresa) throws Exception;

    Locales findById(Long id) throws Exception;

    void save(Locales local) throws Exception;

    void update(Long id, Locales local) throws Exception;

    void delete(Long id) throws Exception;
}
