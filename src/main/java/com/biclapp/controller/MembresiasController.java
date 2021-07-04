package com.biclapp.controller;

import com.biclapp.model.entity.Membresias;
import com.biclapp.service.IMembresiasService;
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
public class MembresiasController {

    @Autowired
    private IMembresiasService membresiasService;

    @GetMapping("/membresias")
    public ResponseEntity<?> getAllMembresias() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Membresias> membresias = membresiasService.findAll();
            response.put("membresias", membresias);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/membresias/{id}")
    public ResponseEntity<?> getMembresia(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Membresias membresia = membresiasService.findById(id);
            response.put("membresia", membresia);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/membresias")
    public ResponseEntity<?> createMembresia(@Valid @RequestBody Membresias membresia, BindingResult result) {
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
            membresiasService.save(membresia);
            response.put("message", "¡Membresia registrada!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/membresias/{id}")
    public ResponseEntity<?> editMembresia(@PathVariable Long id, @Valid @RequestBody Membresias membresia, BindingResult result) {
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
            membresiasService.update(id, membresia);
            response.put("message", "¡Membresia actualizada!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/membresias/{id}")
    public ResponseEntity<?> deleteMembresia(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            membresiasService.delete(id);
            response.put("message", "¡Membresia eliminada!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
