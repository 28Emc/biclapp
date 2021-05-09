package com.biclapp.controller;

import com.biclapp.model.Bicicletas;
import com.biclapp.service.IBicicletasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BicicletasController {

    @Autowired
    private IBicicletasService bicicletasService;

    @GetMapping("/bicicletas")
    public List<Bicicletas> getAllBicicletas() {
        return bicicletasService.findAll();
    }

}
