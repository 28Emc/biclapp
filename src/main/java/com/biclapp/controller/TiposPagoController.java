package com.biclapp.controller;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateAccesorios;
import com.biclapp.model.entity.Accesorios;
import com.biclapp.model.entity.TiposPago;
import com.biclapp.service.ITiposPagoService;
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
public class TiposPagoController {

    @Autowired
    private ITiposPagoService tiposPagoService;

    @GetMapping("/tipos-pago")
    public ResponseEntity<?> getAllTiposPago() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<TiposPago> tiposPago = tiposPagoService.findAll();
            response.put("tipos-pago", tiposPago);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tipos-pago/{id}")
    public ResponseEntity<?> getTipoPago(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            TiposPago tipoPago = tiposPagoService.findById(id);
            response.put("tipo-pago", tipoPago);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tipos-pago")
    public ResponseEntity<?> createTipoPago(@Valid @RequestBody TiposPago tipoPago, BindingResult result) {
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
            tiposPagoService.save(tipoPago);
            response.put("message", "¡Tipo de pago registrado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tipos-pago/{id}")
    public ResponseEntity<?> editTipoPago(@PathVariable Long id, @Valid @RequestBody TiposPago tipoPago, BindingResult result) {
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
            tiposPagoService.update(id, tipoPago);
            response.put("message", "¡Tipo de pago actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tipos-pago/{id}")
    public ResponseEntity<?> deleteTipoPago(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            tiposPagoService.delete(id);
            response.put("message", "¡Tipo de pago eliminado!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
