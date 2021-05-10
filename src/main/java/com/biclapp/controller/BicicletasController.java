package com.biclapp.controller;

import com.biclapp.model.Bicicletas;
import com.biclapp.model.DTO.BicicletaUpdateDTO;
import com.biclapp.model.Locales;
import com.biclapp.service.IBicicletasService;
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
public class BicicletasController {

    @Autowired
    private IBicicletasService bicicletasService;

    @Autowired
    private ILocalesService localesService;

    @GetMapping("/bicicletas")
    public ResponseEntity<?> getAllBicicletas() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Bicicletas> bicicletas = bicicletasService.findAll();
            response.put("bicicletas", bicicletas);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/bicicletas/{id}")
    public ResponseEntity<?> getBicicleta(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Bicicletas bicicleta = bicicletasService.findById(id);
            response.put("bicicleta", bicicleta);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bicicletas")
    public ResponseEntity<?> createBicicleta(@RequestBody Bicicletas bicicleta) {
        Map<String, Object> response = new HashMap<>();
        try {
            localesService.findById(bicicleta.getId_local());
            bicicletasService.save(bicicleta);
            response.put("message", "¡Bicicleta registrada!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bicicletas/{id}")
    public ResponseEntity<?> editBicicleta(@PathVariable Long id, @RequestBody Bicicletas bicicletas) {
        Map<String, Object> response = new HashMap<>();
        try {
            Bicicletas bicicletaFound = bicicletasService.findById(id);
            Locales local = localesService.findById(bicicletas.getId_local());
            bicicletaFound.setId_local(local.getId());
            bicicletaFound.setMarca(bicicletas.getMarca());
            bicicletaFound.setModelo(bicicletas.getModelo());
            bicicletaFound.setStock(bicicletas.getStock());
            bicicletaFound.setCaracteristicas(bicicletas.getCaracteristicas());
            bicicletaFound.setEstado(bicicletas.getEstado());
            bicicletaFound.setFoto(bicicletas.getFoto());
            bicicletasService.save(bicicletaFound);
            response.put("message", "¡Bicicleta actualizada!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bicicletas/estado/{id}")
    public ResponseEntity<?> changeEstadoBicicleta(@PathVariable Long id, @RequestBody BicicletaUpdateDTO updateDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Bicicletas bicicletaFound = bicicletasService.findById(id);
            bicicletaFound.setEstado((updateDTO.getEstado()));
            bicicletasService.save(bicicletaFound);
            response.put("message", "¡Estado de bicicleta actualizada!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bicicletas/{id}")
    public ResponseEntity<?> deleteBicicleta(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            bicicletasService.delete(id);
            response.put("message", "¡Bicicleta eliminada!");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
