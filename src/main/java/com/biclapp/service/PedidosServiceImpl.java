package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreatePedidos;
import com.biclapp.model.DTO.DTODetallePedido;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdatePedidos;
import com.biclapp.model.entity.*;
import com.biclapp.repository.IDetallesPedidoRepository;
import com.biclapp.repository.IPedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private IMembresiasService membresiaService;

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
    public List<Pedidos> findByIdUsuario(Long id_usuario) {
        return repository.findByIdUsuario(id_usuario);
    }


    @Override
    @Transactional(readOnly = true)
    public Pedidos findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("El pedido con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    public List<DetallesPedido> findByUserAndPedido(Long id_usuario, Long id_pedido) throws Exception {
        usuariosService.findById(id_usuario);
        Pedidos pedidoFound = findById(id_pedido);
        return detallesPedidoRepository.findByIdPedido(pedidoFound.getId());
    }

    @Override
    public List<DTODetallePedido> findByUserAndPedidoWithDetail(Long id_usuario, Long id_pedido) throws Exception {
        Pedidos pedidoFound = findById(id_pedido);
        List<DetallesPedido> detallePedidoList = findByUserAndPedido(id_usuario, id_pedido);
        List<DTODetallePedido> nDetallePedido = new ArrayList<>();
        if (pedidoFound.getTipo_pedido().equals("B")) {
            detallePedidoList.forEach(detP -> {
                try {
                    Bicicletas bicicletaFound = bicicletasService.findById(detP.getId_producto());
                    nDetallePedido.add(new DTODetallePedido(detP.getId(), id_pedido, bicicletaFound.getId(),
                            pedidoFound.getTipo_pedido(), bicicletaFound.getMarca(),
                            bicicletaFound.getModelo(), null, null,
                            null, bicicletaFound.getFoto(), detP.getCantidad(),
                            detP.getPrecio(), detP.getTotal()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else if (pedidoFound.getTipo_pedido().equals("A")) {
            detallePedidoList.forEach(detP -> {
                try {
                    Accesorios accesorioFound = accesoriosService.findById(detP.getId_producto());
                    nDetallePedido.add(new DTODetallePedido(detP.getId(), id_pedido, accesorioFound.getId(),
                            pedidoFound.getTipo_pedido(), null, null,
                            accesorioFound.getNombre(), accesorioFound.getDescripcion(), accesorioFound.getTipo(),
                            accesorioFound.getFoto(), detP.getCantidad(), detP.getPrecio(), detP.getTotal()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        return nDetallePedido;
    }

    /*
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
    */

    @Override
    public void save(DTOCreatePedidos createPedidos) throws Exception {
        Pedidos pedidoNew = new Pedidos();
        Usuarios usuarioFound = usuariosService.findById(createPedidos.getId_usuario());
        Empleados empleadoFound = empleadosService.findById(createPedidos.getId_empleado());
        Membresias membresiaFound = membresiaService.findById(createPedidos.getId_membresia());
        if (createPedidos.getTipo_pedido().equals("B")) {
            usuariosService.updateMembresia(usuarioFound.getId(), membresiaFound.getId());
        }
        pedidoNew.setIdUsuario(usuarioFound.getId());
        int contador = findAll().toArray().length;
        pedidoNew.setCodigo(contador + 1);
        pedidoNew.setId_empleado(empleadoFound.getId());
        pedidoNew.setTipo_pedido(createPedidos.getTipo_pedido());
        pedidoNew.setDireccion(createPedidos.getDireccion());
        pedidoNew.setFecha_registro(LocalDateTime.now());
        pedidoNew.setEstado("R");
        repository.save(pedidoNew);

        createPedidos.getDetalles_pedido().forEach(p -> {
            try {
                DetallesPedido detallesPedido = new DetallesPedido();
                if (createPedidos.getTipo_pedido().equals("A")) {
                    Accesorios accesorioFound = accesoriosService.findById(p.getId_producto());
                    detallesPedido.setId_producto(accesorioFound.getId());
                } else if (createPedidos.getTipo_pedido().equals("B")) {
                    Bicicletas bicicletaFound = bicicletasService.findById(p.getId_producto());
                    detallesPedido.setId_producto(bicicletaFound.getId());
                }
                detallesPedido.setIdPedido(pedidoNew.getId());
                detallesPedido.setCantidad(p.getCantidad());
                detallesPedido.setPrecio(p.getPrecio());
                detallesPedido.setTotal(p.getCantidad() * p.getPrecio());
                detallesPedidoRepository.save(detallesPedido);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void createPedidoUser(DTOCreatePedidos createPedidos) throws Exception {
        Pedidos pedidoNew = new Pedidos();
        Usuarios usuarioFound = usuariosService.findById(createPedidos.getId_usuario());
        Membresias membresiaFound = membresiaService.findById(createPedidos.getId_membresia());
        if (createPedidos.getTipo_pedido().equals("B")) {
            usuariosService.updateMembresia(usuarioFound.getId(), membresiaFound.getId());
        }
        pedidoNew.setIdUsuario(usuarioFound.getId());
        int contador = findAll().toArray().length;
        pedidoNew.setCodigo(contador + 1);
        //pedidoNew.setId_empleado(null);
        pedidoNew.setTipo_pedido(createPedidos.getTipo_pedido());
        pedidoNew.setDireccion(createPedidos.getDireccion());
        pedidoNew.setFecha_registro(LocalDateTime.now());
        pedidoNew.setEstado("R");
        repository.save(pedidoNew);

        createPedidos.getDetalles_pedido().forEach(p -> {
            try {
                DetallesPedido detallesPedido = new DetallesPedido();
                if (createPedidos.getTipo_pedido().equals("A")) {
                    Accesorios accesorioFound = accesoriosService.findById(p.getId_producto());
                    detallesPedido.setId_producto(accesorioFound.getId());
                } else if (createPedidos.getTipo_pedido().equals("B")) {
                    Bicicletas bicicletaFound = bicicletasService.findById(p.getId_producto());
                    detallesPedido.setId_producto(bicicletaFound.getId());
                }
                detallesPedido.setIdPedido(pedidoNew.getId());
                detallesPedido.setCantidad(p.getCantidad());
                detallesPedido.setPrecio(p.getPrecio());
                detallesPedido.setTotal(p.getCantidad() * p.getPrecio());
                detallesPedidoRepository.save(detallesPedido);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            int contador = findAll().toArray().length;
            pedidoFound.setIdUsuario(usuarioFound.getId());
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
                    if (updatePedidos.getTipo_pedido().equals("A")) {
                        Accesorios accesorioFound = accesoriosService.findById(p.getId_producto());
                        p.setId_producto(accesorioFound.getId());
                    } else if (updatePedidos.getTipo_pedido().equals("B")) {
                        Bicicletas bicicletaFound = bicicletasService.findById(p.getId_producto());
                        p.setId_producto(bicicletaFound.getId());
                    }
                    detallesPedidoFound.setIdPedido(p.getId());
                    detallesPedidoFound.setCantidad(p.getCantidad());
                    detallesPedidoFound.setPrecio(p.getPrecio());
                    detallesPedidoFound.setTotal(p.getCantidad() * p.getPrecio());
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
        List<DetallesPedido> detallesPedidos = detallesPedidoRepository.findByIdPedido(id);
        detallesPedidos.forEach(p -> detallesPedidoRepository.deleteById(p.getId()));
        repository.deleteById(id);
    }
}
