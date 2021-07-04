package com.biclapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class IndexController {

    @GetMapping("")
    public ResponseEntity<?> home() {
        return new ResponseEntity<>("Biclapp - Api para proyecto de titulación PPI Sise", HttpStatus.OK);
    }
}
