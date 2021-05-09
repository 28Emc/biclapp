package com.biclapp.controller;

import com.biclapp.model.Membresias;
import com.biclapp.service.IMembresiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MembresiasController {

    @Autowired
    private IMembresiasService membresiasService;

    @GetMapping("/membresias")
    public List<Membresias> getAllMembresias() {
        return membresiasService.findAll();
    }

}
