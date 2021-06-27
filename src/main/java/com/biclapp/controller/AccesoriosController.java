package com.biclapp.controller;

import com.biclapp.model.DTO.DTOUpdateAccesorios;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.entity.Accesorios;
import com.biclapp.service.IAccesoriosService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> getAccesorio(@PathVariable Long id) {
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
    public ResponseEntity<?> createAccesorio(@Valid @RequestBody Accesorios accesorio, BindingResult result) {
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
            accesoriosService.save(accesorio);
            response.put("message", "¡Accesorio registrado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/accesorios/{id}")
    public ResponseEntity<?> editAccesorio(@PathVariable Long id, @Valid @RequestBody DTOUpdateAccesorios updateAccesorio, BindingResult result) {
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
    public ResponseEntity<?> changeEstadoAccesorio(@PathVariable Long id, @Valid @RequestBody DTOUpdate update, BindingResult result) {
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
            accesoriosService.updateEstado(id, update);
            response.put("message", "¡Estado de accesorio actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/accesorios/change-photo/{id}")
    public ResponseEntity<?> changePhotoAccesorio(@PathVariable Long id, MultipartFile photoAccesorio) {
        Map<String, Object> response = new HashMap<>();

        try {
            accesoriosService.updatePhotoAccesorio(id, photoAccesorio);
            response.put("message", "¡Foto actualizada!");
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
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
