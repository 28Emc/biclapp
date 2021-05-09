package com.biclapp.service;

import com.biclapp.model.Locales;
import com.biclapp.repository.ILocalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocalesServiceImpl implements ILocalesService {

    @Autowired
    private ILocalesRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Locales> findAll() {
        return (List<Locales>) repository.findAll();
    }
}
