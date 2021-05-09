package com.biclapp.controller;

import com.biclapp.model.Rutas;
import com.biclapp.service.IRutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class RutasController {

    @Autowired
    private IRutasService rutasService;

    @GetMapping("/rutas")
    public List<Rutas> getAllRutas() {
        return rutasService.findAll();
    }

}
