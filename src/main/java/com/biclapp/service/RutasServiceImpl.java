package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdateRutas;
import com.biclapp.model.entity.Rutas;
import com.biclapp.repository.IRutasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RutasServiceImpl implements IRutasService {

    @Autowired
    private IRutasRepository repository;

    @Autowired
    private IUsuariosService usuariosService;

    @Override
    @Transactional(readOnly = true)
    public List<Rutas> findAll() {
        return (List<Rutas>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rutas> findById_usuario(Long id_usuario) {
        return repository.findById_usuario(id_usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Rutas findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("La ruta con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    public void save(Rutas ruta) throws Exception {
        repository.save(ruta);
    }

    @Override
    public void update(Long id, DTOUpdateRutas updateRuta) throws Exception {
        Rutas rutafound = findById(id);
        usuariosService.findById(updateRuta.getId_usuario());
        rutafound.setNombre(updateRuta.getNombre());
        rutafound.setRuta(updateRuta.getRuta());
        rutafound.setTipo_ruta(updateRuta.getTipo_ruta());
        rutafound.setDatos_origen(updateRuta.getDatos_origen());
        rutafound.setDatos_destino(updateRuta.getDatos_destino());
        repository.save(rutafound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
