package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateRecorridos;
import com.biclapp.model.entity.Recorridos;
import com.biclapp.repository.IRecorridosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecorridosServiceImpl implements IRecorridosService {

    @Autowired
    private IRecorridosRepository recorridosRepository;

    @Autowired
    private IUsuariosService usuariosService;

    @Override
    @Transactional(readOnly = true)
    public List<Recorridos> findAll() {
        return (List<Recorridos>) recorridosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Recorridos> findByUser(Long id_usuario) {
        return recorridosRepository.findByIdUsuario(id_usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Recorridos findById(Long id) throws Exception {
        return recorridosRepository.findById(id).orElseThrow(
                () -> new Exception("El recorrido con id ".concat(id.toString()).concat(" no existe."))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Recorridos findByIdAndUser(Long id, Long id_usuario) {
        return recorridosRepository.findByIdAndIdUsuario(id, id_usuario);
    }

    @Override
    public void save(Recorridos recorrido) throws Exception {
        int contador = findAll().toArray().length;
        recorrido.setCodigo(contador + 1);
        recorrido.setEstado("R");
        recorridosRepository.save(recorrido);
    }

    @Override
    public void update(Long id, DTOUpdateRecorridos updateRecorrido) throws Exception {
        Recorridos recorridoFound = findById(id);
        usuariosService.findById(updateRecorrido.getIdUsuario());
        recorridoFound.setKilometros(updateRecorrido.getKilometros());
        recorridoFound.setRitmo_cardiaco(updateRecorrido.getRitmo_cardiaco());
        recorridoFound.setKcal(updateRecorrido.getKcal());
        recorridoFound.setPeso(updateRecorrido.getPeso());
        recorridoFound.setTiempo(updateRecorrido.getTiempo());
        //recorridoFound.setFecha_actualizacion(LocalDateTime.now());
        recorridoFound.setFecha_actualizacion(updateRecorrido.getFecha_actualizacion());
        recorridosRepository.save(recorridoFound);
    }

    @Override
    public void updateEstado(Long id, DTOUpdate update) throws Exception {
        Recorridos recorridoFound = findById(id);
        recorridoFound.setEstado(update.getEstado());
        // TODO: SEGÚN EL ESTADO, REALIZAR EL CÁLCULO DE LOS DEMÁS CAMPOS (KCAL, TIEMPO, ETC)
        recorridosRepository.save(recorridoFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        recorridosRepository.deleteById(id);
    }
}
