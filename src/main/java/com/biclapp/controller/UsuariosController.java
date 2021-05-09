package com.biclapp.controller;

import com.biclapp.model.Usuarios;
import com.biclapp.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UsuariosController {

    @Autowired
    private IUsuariosService usuariosService;

    @GetMapping("/usuarios")
    public List<Usuarios> getAllUsuarios() {
        return usuariosService.findAll();
    }
}
