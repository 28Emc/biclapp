package com.biclapp.service;

import com.biclapp.model.Locales;

import java.util.List;

public interface ILocalesService {

    List<Locales> findAll();

    Locales findById(Long id) throws Exception;

    void save(Locales local) throws Exception;

    void delete(Long id) throws Exception;

}
