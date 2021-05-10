package com.biclapp.controller;

import com.biclapp.model.Bicicletas;
import com.biclapp.model.DTO.RutaUpdateDTO;
import com.biclapp.model.Rutas;
import com.biclapp.model.Usuarios;
import com.biclapp.service.IBicicletasService;
import com.biclapp.service.IRutasService;
import com.biclapp.service.IUsuariosService;
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
public class RutasController {

    @Autowired
    private IRutasService rutasService;

    @Autowired
    private IBicicletasService bicicletasService;

    @Autowired
    private IUsuariosService usuariosService;

    @GetMapping("/rutas")
    public ResponseEntity<?> getAllRutas() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Rutas> rutas = rutasService.findAll();
            response.put("rutas", rutas);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rutas/{id}")
    public ResponseEntity<?> getRuta(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Rutas ruta = rutasService.findById(id);
            response.put("ruta", ruta);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/rutas")
    public ResponseEntity<?> createRuta(@RequestBody Rutas ruta) {
        Map<String, Object> response = new HashMap<>();
        try {
            rutasService.save(ruta);
            response.put("message", "¡Ruta registrada!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/rutas/{id}")
    public ResponseEntity<?> editRuta(@PathVariable Long id, @RequestBody Rutas ruta) {
        Map<String, Object> response = new HashMap<>();
        try {
            Rutas rutaFound = rutasService.findById(id);
            rutaFound.setId(ruta.getId());
            Usuarios usuarioFound = usuariosService.findById(ruta.getId_usuario());
            rutaFound.setId_usuario(usuarioFound.getId());
            Bicicletas bicicletaFound = bicicletasService.findById(ruta.getId_bicicleta());
            rutaFound.setId_bicicleta(bicicletaFound.getId());
            rutaFound.setNombre(ruta.getNombre());
            rutaFound.setEstado(ruta.getEstado());
            rutasService.save(rutaFound);
            response.put("message", "¡Ruta actualizada!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/rutas/estado/{id}")
    public ResponseEntity<?> changeEstadoRuta(@PathVariable Long id, @RequestBody RutaUpdateDTO updateDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Rutas rutaFound = rutasService.findById(id);
            rutaFound.setEstado((updateDTO.getEstado()));
            rutasService.save(rutaFound);
            response.put("message", "¡Estado de ruta actualizada!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/rutas/{id}")
    public ResponseEntity<?> deleteRuta(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            rutasService.delete(id);
            response.put("message", "¡Ruta eliminada!");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
