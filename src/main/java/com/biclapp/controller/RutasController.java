package com.biclapp.controller;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateRutas;
import com.biclapp.model.entity.Rutas;
import com.biclapp.model.entity.Usuarios;
import com.biclapp.service.IBicicletasService;
import com.biclapp.service.IRutasService;
import com.biclapp.service.IUsuariosService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> createRuta(@Valid @RequestBody Rutas ruta, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

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
    public ResponseEntity<?> editRuta(@PathVariable Long id, @Valid @RequestBody DTOUpdateRutas updateRutas, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            rutasService.update(id, updateRutas);
            response.put("message", "¡Ruta actualizada!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/rutas/estado/{id}")
    public ResponseEntity<?> changeEstadoRuta(@PathVariable Long id, @Valid @RequestBody DTOUpdate DTOUpdate, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Rutas rutaFound = rutasService.findById(id);
            rutaFound.setEstado((DTOUpdate.getEstado()));
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
