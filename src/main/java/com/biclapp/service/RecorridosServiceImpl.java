package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateRecorridos;
import com.biclapp.model.entity.Recorridos;
import com.biclapp.repository.IRecorridosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return recorridosRepository.findById_usuario(id_usuario);
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
        return recorridosRepository.findByIdAndId_usuario(id, id_usuario);
    }

    @Override
    public void save(Recorridos recorrido) throws Exception {
        recorridosRepository.save(recorrido);
    }

    @Override
    public void update(Long id, DTOUpdateRecorridos updateRecorrido) throws Exception {
        Recorridos recorridoFound = findById(id);
        usuariosService.findById(updateRecorrido.getId_usuario());
        recorridoFound.setKilometros(updateRecorrido.getKilometros());
        recorridoFound.setRitmo_cardiaco(updateRecorrido.getRitmo_cardiaco());
        recorridoFound.setKcal(updateRecorrido.getKcal());
        recorridoFound.setPeso(updateRecorrido.getPeso());
        recorridoFound.setTiempo(updateRecorrido.getTiempo());
        recorridosRepository.save(recorridoFound);
    }

    @Override
    public void updateEstado(Long id, DTOUpdate update) throws Exception {
        Recorridos recorridoFound = findById(id);
        recorridoFound.setEstado(update.getEstado());
        recorridosRepository.save(recorridoFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        recorridosRepository.deleteById(id);
    }
}
