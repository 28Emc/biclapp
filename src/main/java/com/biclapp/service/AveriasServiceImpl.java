package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateAverias;
import com.biclapp.model.entity.Averias;
import com.biclapp.model.entity.Usuarios;
import com.biclapp.repository.IAveriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AveriasServiceImpl implements IAveriasService {

    @Autowired
    private IAveriasRepository averiasRepository;

    @Autowired
    private IUsuariosService usuariosService;

    @Override
    @Transactional(readOnly = true)
    public List<Averias> findAll() {
        return (List<Averias>) averiasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Averias findById(Long id) throws Exception {
        return averiasRepository.findById(id).orElseThrow(
                () -> new Exception("La avería con el id ".concat(id.toString()).concat(" no existe"))
        );
    }

    @Override
    public void save(Averias averia) throws Exception {
        usuariosService.findById(averia.getId_usuario());
        int contador = findAll().toArray().length;
        averia.setCodigo(contador + 1);
        averia.setEstado("P");
        averiasRepository.save(averia);
    }

    @Override
    public void update(Long id, DTOUpdateAverias updateAveria) throws Exception {
        Averias averiaFound = findById(id);
        Usuarios usuarioFound = usuariosService.findById(averiaFound.getId_usuario());
        averiaFound.setId_usuario(usuarioFound.getId());
        averiaFound.setCodigo(updateAveria.getCodigo());
        averiaFound.setDireccion(updateAveria.getDireccion());
        averiaFound.setMotivo(updateAveria.getMotivo());
        averiaFound.setFecha_registro(updateAveria.getFecha_registro()); // POR AHORA, ACEPTO LA FECHA DEL CLIENTE
        averiaFound.setEstado(updateAveria.getEstado());
        averiasRepository.save(averiaFound);
    }

    @Override
    public void updateEstado(Long id, DTOUpdate update) throws Exception {
        Averias averiaFound = findById(id);
        averiaFound.setEstado(update.getEstado());
        averiasRepository.save(averiaFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        averiasRepository.deleteById(id);
    }
}
