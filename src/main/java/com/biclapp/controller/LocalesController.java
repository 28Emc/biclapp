package com.biclapp.controller;

import com.biclapp.model.Locales;
import com.biclapp.service.ILocalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LocalesController {

    @Autowired
    private ILocalesService localesService;

    @GetMapping("/locales")
    public List<Locales> getAllLocales() {
        return localesService.findAll();
    }

}
