package com.biclapp.controller;

import com.biclapp.model.Pedidos;
import com.biclapp.service.IPedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PedidosController {

    @Autowired
    private IPedidosService pedidosService;

    @GetMapping("/pedidos")
    public List<Pedidos> getAllPedidos() {
        return pedidosService.findAll();
    }

}
