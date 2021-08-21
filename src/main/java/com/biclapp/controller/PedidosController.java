package com.biclapp.controller;

import com.biclapp.model.DTO.DTOCreatePedidos;
import com.biclapp.model.DTO.DTODetallePedido;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdatePedidos;
import com.biclapp.model.entity.Pedidos;
import com.biclapp.service.IDetallesPedidoService;
import com.biclapp.service.IPedidosService;
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
public class PedidosController {

    @Autowired
    private IPedidosService pedidosService;

    @Autowired
    private IDetallesPedidoService detallesPedidoService;

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

    @GetMapping("/pedidos-user/{id_usuario}/{id_pedido}")
    public ResponseEntity<?> getAllDetallesPedidoByUser(@PathVariable Long id_usuario, @PathVariable Long id_pedido) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<DTODetallePedido> detPedido = pedidosService.findByUserAndPedidoWithDetail(id_usuario, id_pedido);
            response.put("detalles-pedido", detPedido);
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

    @GetMapping("/pedidos-user/{id}")
    public ResponseEntity<?> getPedidosByUser(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Pedidos> pedidos = pedidosService.findByIdUsuario(id);
            response.put("pedidos", pedidos);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pedido-bike-exist/{idUsuario}")
    public ResponseEntity<?> isPedidoBikeFound(@PathVariable Long idUsuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean pedidoBikeFound = pedidosService.findIfExistPedidoBicicleta(idUsuario);
            response.put("pedido-bike", pedidoBikeFound);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    /*
    @GetMapping("/pedidos-details")
    public ResponseEntity<?> getAllPedidosWithDetails() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Pedidos> pedidos = pedidosService.findAllWithDetails();
            response.put("pedidos", pedidos);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pedidos-details/{id}")
    public ResponseEntity<?> getOnePedidoWithDetails(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pedidos pedido = pedidosService.findByIdWithDetails(id);
            response.put("pedido", pedido);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pedidos-user/{id}")
    public ResponseEntity<?> getPedidosByUser(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Pedidos> pedidos = pedidosService.findByUserWithDetails(id);
            response.put("pedidos", pedidos);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pedidos-user/{id_pedido}/{id_usuario}")
    public ResponseEntity<?> getPedidoByUserWithDetails(@PathVariable Long id_pedido, @PathVariable Long id_usuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pedidos pedido = pedidosService.findOneByUserWithDetails(id_pedido, id_usuario);
            response.put("pedido", pedido);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    */

    @PostMapping("/pedidos")
    public ResponseEntity<?> createPedido(@Valid @RequestBody DTOCreatePedidos createPedidos, BindingResult result) {
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
            pedidosService.save(createPedidos);
            response.put("message", "¡Pedido registrado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("e = " + e);
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping({"/pedido-bike", "/pedido-user"})
    public ResponseEntity<?> createPedidoBike(@Valid @RequestBody DTOCreatePedidos createPedidos, BindingResult result) {
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
            Pedidos pedidoCreated = pedidosService.createPedidoUser(createPedidos);

            if (pedidoCreated.getTipo_pedido().equals("B")) {
                response.put("pedido-bike", true);
            }
            response.put("message", "¡Pedido registrado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            String messageFormat = e.getMessage();
            /*if (messageFormat.contains("Exception:")) {
                messageFormat = messageFormat.substring(messageFormat.indexOf(": ")).replace(":", "").trim();
            }*/
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", messageFormat);
            System.out.println("e = " + e);
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pedidos/{id}")
    public ResponseEntity<?> editPedido(@PathVariable Long id, @Valid @RequestBody DTOUpdatePedidos updatePedidos, BindingResult result) {
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
            pedidosService.update(id, updatePedidos);
            response.put("message", "¡Pedido actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("e = " + e);
            e.printStackTrace();
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pedidos/estado/{id}")
    public ResponseEntity<?> changeEstadoPedido(@PathVariable Long id, @Valid @RequestBody DTOUpdate update, BindingResult result) {
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
            Pedidos pedidoFound = pedidosService.findById(id);
            if (pedidoFound.getTipo_pedido().equals("B") && update.getEstado().equals("B")) {
                response.put("pedido-bike", false);
            }

            pedidosService.updateEstado(id, update);
            response.put("message", "¡Estado del pedido actualizado!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("e = " + e);
            e.printStackTrace();
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
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("e = " + e);
            e.printStackTrace();
            response.put("error", ExceptionUtils.getRootCauseMessage(e));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
