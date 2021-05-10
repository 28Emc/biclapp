package com.biclapp.controller;

import com.biclapp.model.Bicicletas;
import com.biclapp.model.DTO.PedidoUpdateDTO;
import com.biclapp.model.Pedidos;
import com.biclapp.service.IBicicletasService;
import com.biclapp.service.IPedidosService;
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
public class PedidosController {

    @Autowired
    private IPedidosService pedidosService;

    @Autowired
    private IBicicletasService bicicletasService;

    @GetMapping("/pedidos")
    public ResponseEntity<?> getAllPedidos() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Pedidos> pedidos = pedidosService.findAll();
            response.put("pedidos", pedidos);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<?> getPedido(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pedidos pedido = pedidosService.findById(id);
            response.put("pedido", pedido);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pedidos")
    public ResponseEntity<?> createPedido(@RequestBody Pedidos pedido) {
        Map<String, Object> response = new HashMap<>();
        try {
            pedidosService.save(pedido);
            response.put("message", "¡Pedido registrado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pedidos/{id}")
    public ResponseEntity<?> editPedido(@PathVariable Long id, @RequestBody Pedidos pedido) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pedidos pedidoFound = pedidosService.findById(id);
            if (pedidoFound.getEstado().equals("R")) {
                pedidoFound.setId(pedido.getId());
                // TODO: LA EDICIÓN SE PUEDE HACER SOLO EN ESTADO "REGISTRADO"
                // TODO: EN TODO CASO, SE DEBERÍA DAR DE BAJA EL PEDIDO ACTUAL Y
                // TODO: REGISTRAR OTRO PEDIDO
                Bicicletas bicicletaFound = bicicletasService.findById(pedido.getId_bicicleta());
                pedidoFound.setId_bicicleta(bicicletaFound.getId());
                pedidoFound.setEstado(pedidoFound.getEstado());
                pedidosService.save(pedidoFound);
                response.put("message", "¡Pedido actualizado!");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                response.put("message", "¡Acción no permitida! Verificar el estado del pedido.");
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pedidos/estado/{id}")
    public ResponseEntity<?> changeEstadoPedido(@PathVariable Long id, @RequestBody PedidoUpdateDTO updateDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pedidos pedidoFound = pedidosService.findById(id);
            pedidoFound.setEstado((updateDTO.getEstado()));
            pedidosService.save(pedidoFound);
            response.put("message", "¡Estado del pedido actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            pedidosService.delete(id);
            response.put("message", "¡Pedido eliminado!");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
