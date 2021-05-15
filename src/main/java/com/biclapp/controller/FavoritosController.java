package com.biclapp.controller;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateAccesorios;
import com.biclapp.model.DTO.DTOUpdateFavoritos;
import com.biclapp.model.entity.Accesorios;
import com.biclapp.model.entity.Favoritos;
import com.biclapp.service.IFavoritosService;
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
public class FavoritosController {

    @Autowired
    private IFavoritosService favoritosService;

    @GetMapping("/favoritos")
    public ResponseEntity<?> getAllFavoritos() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Favoritos> favoritos = favoritosService.findAll();
            response.put("favoritos", favoritos);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/favoritos-user/{id}")
    public ResponseEntity<?> getFavoritoByUser(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Favoritos> favoritos = favoritosService.findByUser(id);
            response.put("favoritos-user", favoritos);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/favoritos/{id}")
    public ResponseEntity<?> getFavorito(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Favoritos favorito = favoritosService.findById(id);
            response.put("favorito", favorito);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/favoritos")
    public ResponseEntity<?> createFavorito(@Valid @RequestBody Favoritos favorito, BindingResult result) {
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
            favoritosService.save(favorito);
            response.put("message", "¡Favorito registrado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/favoritos/{id}")
    public ResponseEntity<?> editFavorito(@PathVariable Long id, @Valid @RequestBody DTOUpdateFavoritos updateFavorito, BindingResult result) {
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
            favoritosService.update(id, updateFavorito);
            response.put("message", "¡Favorito actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/favoritos/{id}")
    public ResponseEntity<?> deleteFavorito(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            favoritosService.delete(id);
            response.put("message", "¡Favorito eliminado!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
