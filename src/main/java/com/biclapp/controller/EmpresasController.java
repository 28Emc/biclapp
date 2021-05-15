package com.biclapp.controller;

import com.biclapp.model.entity.Accesorios;
import com.biclapp.model.entity.Empresas;
import com.biclapp.repository.IEmpresaService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class EmpresasController {

    @Autowired
    private IEmpresaService empresaService;

    @GetMapping("/empresas")
    public ResponseEntity<?> getAllEmpresas() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Empresas> empresas = empresaService.findAll();
            response.put("empresas", empresas);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/empresas/{id}")
    public ResponseEntity<?> getEmpresa(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Empresas empresas = empresaService.findById(id);
            response.put("empresa", empresas);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
