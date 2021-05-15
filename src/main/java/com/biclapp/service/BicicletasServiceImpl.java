package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateBicicletas;
import com.biclapp.model.entity.Bicicletas;
import com.biclapp.model.entity.Locales;
import com.biclapp.repository.IBicicletasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BicicletasServiceImpl implements IBicicletasService {

    @Autowired
    private IBicicletasRepository bicicletasRepository;

    @Autowired
    private ILocalesService localesService;

    @Override
    @Transactional(readOnly = true)
    public List<Bicicletas> findAll() {
        return (List<Bicicletas>) bicicletasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Bicicletas findById(Long id) throws Exception {
        return bicicletasRepository.findById(id).orElseThrow(() -> new Exception("La bicicleta con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    public void save(Bicicletas bicicleta) throws Exception {
        bicicletasRepository.save(bicicleta);
    }

    @Override
    public void update(Long id, DTOUpdateBicicletas updateBicicleta) throws Exception {
        Bicicletas bicicletaFound = findById(id);
        Locales localFound = localesService.findById(updateBicicleta.getId_local());
        bicicletaFound.setId_local(localFound.getId());
        bicicletaFound.setMarca(updateBicicleta.getMarca());
        bicicletaFound.setModelo(updateBicicleta.getModelo());
        bicicletaFound.setStock(updateBicicleta.getStock());
        bicicletaFound.setDescripcion(updateBicicleta.getDescripcion());
        bicicletaFound.setFoto(updateBicicleta.getFoto());
        bicicletaFound.setColor(updateBicicleta.getColor());
        bicicletasRepository.save(bicicletaFound);
    }

    @Override
    public void updateEstado(Long id, DTOUpdate update) throws Exception {
        Bicicletas bicicletaFound = findById(id);
        bicicletaFound.setEstado(update.getEstado());
        bicicletasRepository.save(bicicletaFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        bicicletasRepository.deleteById(id);
    }
}
