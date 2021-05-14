package com.biclapp.controller;

import com.biclapp.model.entity.Roles;
import com.biclapp.service.IRolesService;
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
public class RolesController {

    @Autowired
    private IRolesService rolesService;

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Roles> roles = rolesService.findAll();
            response.put("roles", roles);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<?> getRol(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Roles rol = rolesService.findById(id);
            response.put("rol", rol);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/roles")
    public ResponseEntity<?> createRol(@RequestBody Roles rol) {
        Map<String, Object> response = new HashMap<>();
        try {
            rolesService.save(rol);
            response.put("message", "¡Rol registrado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<?> editRoles(@PathVariable Long id, @RequestBody Roles rol) {
        Map<String, Object> response = new HashMap<>();
        try {
            Roles rolFound = rolesService.findById(id);
            rolFound.setId(rol.getId());
            rolFound.setRol(rol.getRol());
            rolesService.save(rolFound);
            response.put("message", "¡Rol actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            rolesService.delete(id);
            response.put("message", "¡Rol eliminado!");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
