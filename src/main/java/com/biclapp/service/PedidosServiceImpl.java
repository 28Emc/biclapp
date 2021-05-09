package com.biclapp.service;

import com.biclapp.model.Pedidos;
import com.biclapp.repository.IPedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidosServiceImpl implements IPedidosService {

    @Autowired
    private IPedidosRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Pedidos> findAll() {
        return (List<Pedidos>) repository.findAll();
    }
}
