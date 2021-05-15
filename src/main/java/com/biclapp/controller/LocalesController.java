package com.biclapp.controller;

import com.biclapp.model.entity.Locales;
import com.biclapp.service.ILocalesService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class LocalesController {

    @Autowired
    private ILocalesService localesService;

    @GetMapping("/locales")
    public ResponseEntity<?> getAllLocales() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Locales> locales = localesService.findAll();
            response.put("locales", locales);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/locales-empresa/{id}")
    public ResponseEntity<?> getLocalesByEmpresa(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Locales> locales = localesService.findByEmpresa(id);
            response.put("locales", locales);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/locales/{id}")
    public ResponseEntity<?> getLocal(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Locales local = localesService.findById(id);
            response.put("local", local);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/locales")
    public ResponseEntity<?> createLocal(@RequestBody Locales local) {
        Map<String, Object> response = new HashMap<>();
        try {
            localesService.save(local);
            response.put("message", "¡Local registrado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/locales/{id}")
    public ResponseEntity<?> editLocal(@PathVariable Long id, @RequestBody Locales local) {
        Map<String, Object> response = new HashMap<>();
        try {
            localesService.update(id, local);
            response.put("message", "¡Local actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/locales/{id}")
    public ResponseEntity<?> deleteLocal(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            localesService.delete(id);
            response.put("message", "¡Local eliminado!");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
