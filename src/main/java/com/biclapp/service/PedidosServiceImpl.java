package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreatePedidos;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdatePedidos;
import com.biclapp.model.entity.*;
import com.biclapp.repository.IDetallesPedidoRepository;
import com.biclapp.repository.IPedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidosServiceImpl implements IPedidosService {

    @Autowired
    private IPedidosRepository repository;

    @Autowired
    private IDetallesPedidoRepository detallesPedidoRepository;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private IEmpleadosService empleadosService;

    @Autowired
    private IBicicletasService bicicletasService;

    @Autowired
    private IAccesoriosService accesoriosService;

    @Override
    @Transactional(readOnly = true)
    public List<Pedidos> findAll() {
        return (List<Pedidos>) repository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Pedidos findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("El pedido con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedidos> findAllWithDetails() {
        return repository.findAllWithDetails();
    }

    @Override
    @Transactional(readOnly = true)
    public Pedidos findByIdWithDetails(Long id_pedido) {
        return repository.findByIdWithDetails(id_pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedidos> findByUserWithDetails(Long id_usuario) {
        return repository.findByUserWithDetails(id_usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Pedidos findOneByUserWithDetails(Long id_pedido, Long id_usuario) {
        return repository.findOneByUserWithDetails(id_pedido, id_usuario);
    }

    @Override
    public void save(DTOCreatePedidos createPedidos) throws Exception {
        Pedidos pedidoNew = new Pedidos();
        Usuarios usuarioFound = usuariosService.findById(createPedidos.getId_usuario());
        Empleados empleadoFound = empleadosService.findById(createPedidos.getId_empleado());
        if (createPedidos.getTipo_pedido().equals("A")) {
            Accesorios accesorioFound = accesoriosService.findById(createPedidos.getId_producto());
            pedidoNew.setId_producto(accesorioFound.getId());
        } else if (createPedidos.getTipo_pedido().equals("B")) {
            Bicicletas bicicletaFound = bicicletasService.findById(createPedidos.getId_producto());
            pedidoNew.setId_producto(bicicletaFound.getId());
        }
        int contador = findAll().toArray().length;
        pedidoNew.setId_usuario(usuarioFound.getId());
        pedidoNew.setCodigo("P-".concat(String.valueOf(contador)));
        pedidoNew.setId_empleado(empleadoFound.getId());
        pedidoNew.setTipo_pedido(createPedidos.getTipo_pedido());
        pedidoNew.setDireccion(createPedidos.getDireccion());
        pedidoNew.setFecha_registro(createPedidos.getFecha_registro());
        repository.save(pedidoNew);

        createPedidos.getDetalles_pedido().forEach(p -> {
            DetallesPedido detallesPedido = new DetallesPedido();
            detallesPedido.setId_pedido(pedidoNew.getId());
            detallesPedido.setCantidad(p.getCantidad());
            detallesPedido.setPrecio(p.getPrecio());
            detallesPedido.setTotal(p.getTotal());
            detallesPedidoRepository.save(detallesPedido);
        });
    }

    @Override
    public void update(Long id, DTOUpdatePedidos updatePedidos) throws Exception {
        Pedidos pedidoFound = findById(id);
        if (pedidoFound.getEstado().equals("R")) {
            pedidoFound.setId(updatePedidos.getId_pedido());
            // LA EDICIÓN SE PUEDE HACER SOLO EN ESTADO "REGISTRADO"
            // DE TODAS MANERAS, SE DEBERÍA DAR DE BAJA EL PEDIDO ACTUAL Y
            // REGISTRAR OTRO PEDIDO
            Usuarios usuarioFound = usuariosService.findById(updatePedidos.getId_usuario());
            Empleados empleadoFound = empleadosService.findById(updatePedidos.getId_empleado());
            if (updatePedidos.getTipo_pedido().equals("A")) {
                Accesorios accesorioFound = accesoriosService.findById(updatePedidos.getId_producto());
                pedidoFound.setId_producto(accesorioFound.getId());
            } else if (updatePedidos.getTipo_pedido().equals("B")) {
                Bicicletas bicicletaFound = bicicletasService.findById(updatePedidos.getId_producto());
                pedidoFound.setId_producto(bicicletaFound.getId());
            }
            int contador = findAll().toArray().length;
            pedidoFound.setId_usuario(usuarioFound.getId());
            pedidoFound.setCodigo(updatePedidos.getCodigo());
            pedidoFound.setId_empleado(empleadoFound.getId());
            pedidoFound.setTipo_pedido(updatePedidos.getTipo_pedido());
            pedidoFound.setDireccion(updatePedidos.getDireccion());
            pedidoFound.setFecha_registro(updatePedidos.getFecha_registro());
            repository.save(pedidoFound);

            updatePedidos.getDetalles_pedido().forEach(p -> {
                try {
                    DetallesPedido detallesPedidoFound = detallesPedidoRepository.findById(p.getId()).orElseThrow(
                            () -> new Exception("El detalle pedido con el id ".concat(p.getId().toString()).concat(" no existe."))
                    );
                    detallesPedidoFound.setId_pedido(p.getId());
                    detallesPedidoFound.setCantidad(p.getCantidad());
                    detallesPedidoFound.setPrecio(p.getPrecio());
                    detallesPedidoFound.setTotal(p.getTotal());
                    detallesPedidoRepository.save(detallesPedidoFound);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } else {
            throw new Exception("Pedido no actualizable, verificar que el estado del pedido sea el correcto.");
        }
    }

    @Override
    public void updateEstado(Long id, DTOUpdate update) throws Exception {
        Pedidos pedidoFound = findById(id);
        pedidoFound.setEstado((update.getEstado()));
        repository.save(pedidoFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
