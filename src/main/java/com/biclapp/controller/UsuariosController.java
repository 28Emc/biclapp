package com.biclapp.controller;

import com.biclapp.model.DTO.DTOCreateUsuarios;
import com.biclapp.model.entity.Membresias;
import com.biclapp.model.entity.Roles;
import com.biclapp.model.entity.Usuarios;
import com.biclapp.service.IMembresiasService;
import com.biclapp.service.IRolesService;
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
public class UsuariosController {

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private IRolesService rolesService;

    @Autowired
    private IMembresiasService membresiasService;

    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllUsuarios() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Usuarios> usuarios = usuariosService.findAll();
            response.put("usuarios", usuarios);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuarios usuario = usuariosService.findById(id);
            response.put("usuario", usuario);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> createUsuario(@RequestBody DTOCreateUsuarios createDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            // TODO: POR MIENTRAS SE ESTÁ VALIDANDO EL USUARIO CON ESTADO "ACTIVO", SE DEBERÍA VALIDAR MEDIANTE
            // TODO: TOKEN/CELULAR/EMAIL.
            Roles rolFound = rolesService.findById(createDTO.getId_rol());
            Usuarios usuarioNew = new Usuarios(rolFound, createDTO.getId_membresia(), createDTO.getNombres(),
                    createDTO.getApellidos(), createDTO.getNro_documento(), createDTO.getCelular(),
                    createDTO.getDireccion(), createDTO.getUsername(), createDTO.getPassword(),
                    createDTO.getEstado(), createDTO.getFoto(), true);
            usuariosService.save(usuarioNew);
            response.put("message", "¡Usuario registrado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> editUsuario(@PathVariable Long id, @RequestBody Usuarios usuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuarios usuarioFound = usuariosService.findById(id);
            usuarioFound.setId(usuario.getId());
            Roles roleFound = rolesService.findById(usuario.getRol().getId());
            usuarioFound.setRol(roleFound);
            Membresias membresiaFound = membresiasService.findById(usuario.getId_membresia());
            usuarioFound.setId_membresia(membresiaFound.getId());
            usuarioFound.setNombres(usuarioFound.getNombres());
            usuarioFound.setApellidos(usuarioFound.getApellidos());
            usuarioFound.setNro_documento(usuarioFound.getNro_documento());
            usuarioFound.setCelular(usuarioFound.getCelular());
            usuarioFound.setDireccion(usuario.getDireccion());
            usuarioFound.setUsername(usuarioFound.getUsername());
            usuarioFound.setPassword(usuarioFound.getPassword());
            usuarioFound.setEstado(usuarioFound.getEstado());
            usuarioFound.setFoto(usuarioFound.getFoto());
            usuarioFound.setActivo(usuarioFound.isActivo());
            usuariosService.save(usuarioFound);
            response.put("message", "¡Usuario actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            usuariosService.delete(id);
            response.put("message", "¡Usuario eliminado!");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
