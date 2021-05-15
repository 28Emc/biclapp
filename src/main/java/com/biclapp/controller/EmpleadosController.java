package com.biclapp.controller;

import com.biclapp.model.DTO.DTOCreateEmpleados;
import com.biclapp.model.entity.Empleados;
import com.biclapp.service.IEmpleadosService;
import com.biclapp.service.IRolesService;
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
public class EmpleadosController {

    @Autowired
    private IEmpleadosService empleadosService;

    @Autowired
    private IRolesService rolesService;

    @GetMapping("/empleados")
    public ResponseEntity<?> getAllEmpleados() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Empleados> empleados = empleadosService.findAll();
            response.put("empleados", empleados);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<?> getEmpleado(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Empleados empleado = empleadosService.findById(id);
            response.put("empleado", empleado);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/empleados")
    public ResponseEntity<?> createEmpleado(@Valid @RequestBody DTOCreateEmpleados createDTO, BindingResult result) {
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
            empleadosService.save(createDTO);
            response.put("message", "¡Empleado registrado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<?> editEmpleado(@PathVariable Long id, @Valid @RequestBody DTOCreateEmpleados createEmpleados, BindingResult result) {
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
            empleadosService.update(id, createEmpleados);
            response.put("message", "¡Usuario actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            empleadosService.delete(id);
            response.put("message", "¡Empleado eliminado!");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
