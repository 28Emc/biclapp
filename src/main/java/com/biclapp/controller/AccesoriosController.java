package com.biclapp.controller;

import com.biclapp.model.DTO.DTOCreateAccesorios;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.entity.Accesorios;
import com.biclapp.model.entity.Bicicletas;
import com.biclapp.model.entity.Locales;
import com.biclapp.service.IAccesoriosService;
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
public class AccesoriosController {

    @Autowired
    private IAccesoriosService accesoriosService;

    @GetMapping("/accesorios")
    public ResponseEntity<?> getAllAccesorios() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Accesorios> accesorios = accesoriosService.findAll();
            response.put("accesorios", accesorios);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/accesorios/{id}")
    public ResponseEntity<?> getAccesorios(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Accesorios accesorio = accesoriosService.findById(id);
            response.put("accesorio", accesorio);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/accesorios")
    public ResponseEntity<?> createAccesorio(@RequestBody DTOCreateAccesorios createAccesorios) {
        Map<String, Object> response = new HashMap<>();
        try {
            accesoriosService.save(createAccesorios);
            response.put("message", "¡Accesorio registrado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/accesorios/{id}")
    public ResponseEntity<?> editAccesorio(@PathVariable Long id, @RequestBody DTOCreateAccesorios updateAccesorio) {
        Map<String, Object> response = new HashMap<>();
        try {
            accesoriosService.update(id, updateAccesorio);
            response.put("message", "¡Accesorio actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/accesorios/estado/{id}")
    public ResponseEntity<?> changeEstadoAccesorio(@PathVariable Long id, @RequestBody DTOUpdate update) {
        Map<String, Object> response = new HashMap<>();
        try {
            accesoriosService.updateEstado(id, update);
            response.put("message", "¡Estado de accesorio actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/accesorios/{id}")
    public ResponseEntity<?> deleteAccesorio(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            accesoriosService.delete(id);
            response.put("message", "¡Accesorio eliminado!");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}