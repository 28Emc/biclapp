package com.biclapp.controller;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateAccesorios;
import com.biclapp.model.DTO.DTOUpdateRecorridos;
import com.biclapp.model.entity.Accesorios;
import com.biclapp.model.entity.Recorridos;
import com.biclapp.service.IRecorridosService;
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
public class RecorridosController {

    @Autowired
    private IRecorridosService recorridosService;

    @GetMapping("/recorridos")
    public ResponseEntity<?> getAllRecorridos() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Recorridos> recorridos = recorridosService.findAll();
            response.put("recorridos", recorridos);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recorridos/{id}")
    public ResponseEntity<?> getRecorrido(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Recorridos recorrido = recorridosService.findById(id);
            response.put("recorrido", recorrido);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recorridos-user/{id_usuario}")
    public ResponseEntity<?> getAllRecorridosByUser(@PathVariable Long id_usuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Recorridos> recorridos = recorridosService.findByUser(id_usuario);
            response.put("recorridos", recorridos);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recorridos-user/{id_recorrido}/{id_usuario}")
    public ResponseEntity<?> getOneRecorridoByIdAndUser(@PathVariable Long id_recorrido, @PathVariable Long id_usuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            Recorridos recorrido = recorridosService.findByIdAndUser(id_recorrido, id_usuario);
            response.put("recorrido", recorrido);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/recorridos")
    public ResponseEntity<?> createRecorrido(@Valid @RequestBody Recorridos recorrido, BindingResult result) {
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
            recorridosService.save(recorrido);
            response.put("message", "¡Recorrido registrado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/recorridos/{id}")
    public ResponseEntity<?> editRecorrido(@PathVariable Long id, @Valid @RequestBody DTOUpdateRecorridos updateRecorrido, BindingResult result) {
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
            recorridosService.update(id, updateRecorrido);
            response.put("message", "¡Recorrido actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/recorridos/estado/{id}")
    public ResponseEntity<?> changeEstadoRecorrido(@PathVariable Long id, @Valid @RequestBody DTOUpdate update, BindingResult result) {
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
            recorridosService.updateEstado(id, update);
            response.put("message", "¡Estado de recorrido actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/recorridos/{id}")
    public ResponseEntity<?> deleteRecorrido(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            recorridosService.delete(id);
            response.put("message", "¡Recorrido eliminado!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
