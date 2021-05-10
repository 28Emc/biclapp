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

    @Override
    @Transactional(readOnly = true)
    public Pedidos findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("El pedido con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    public void save(Pedidos pedido) throws Exception {
        repository.save(pedido);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
