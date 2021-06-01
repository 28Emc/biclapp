package com.biclapp.controller;

import com.biclapp.model.DTO.DTOCreateBicicletas;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateBicicletas;
import com.biclapp.model.entity.Bicicletas;
import com.biclapp.model.entity.Locales;
import com.biclapp.service.IBicicletasService;
import com.biclapp.service.ILocalesService;
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
    public ResponseEntity<?> createBicicleta(@Valid DTOCreateBicicletas createBicicleta, BindingResult result) {
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
            bicicletasService.save(createBicicleta);
            response.put("message", "¡Bicicleta registrada!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bicicletas/{id}")
    public ResponseEntity<?> editBicicleta(@PathVariable Long id, @Valid @RequestBody DTOUpdateBicicletas updateBicicleta, BindingResult result) {
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
            bicicletasService.update(id, updateBicicleta);
            response.put("message", "¡Bicicleta actualizada!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bicicletas/estado/{id}")
    public ResponseEntity<?> changeEstadoBicicleta(@PathVariable Long id, @Valid @RequestBody DTOUpdate update, BindingResult result) {
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
            bicicletasService.updateEstado(id, update);
            response.put("message", "¡Estado de bicicleta actualizada!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bicicletas/change-photo/{id}")
    public ResponseEntity<?> changePhotoBicicleta(@PathVariable Long id, MultipartFile photoBicicleta) {
        Map<String, Object> response = new HashMap<>();

        try {
            bicicletasService.updatePhotoBicicleta(id, photoBicicleta);
            response.put("message", "¡Foto actualizada!");
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
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
