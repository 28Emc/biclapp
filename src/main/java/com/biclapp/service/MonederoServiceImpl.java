package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdateMonederos;
import com.biclapp.model.entity.Monederos;
import com.biclapp.repository.IMonederosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MonederoServiceImpl implements IMonederosService {

    @Autowired
    private IMonederosRepository monederosRepository;

    @Autowired
    private IUsuariosService usuariosService;

    @Override
    @Transactional(readOnly = true)
    public List<Monederos> findAll() {
        return (List<Monederos>) monederosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Monederos findById(Long id) throws Exception {
        return monederosRepository.findById(id).orElseThrow(
                () -> new Exception("El monedero con id ".concat(id.toString()).concat(" no existe."))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Monederos findByUser(Long id_usuario) throws Exception {
        usuariosService.findById(id_usuario);
        return monederosRepository.findByIdUsuario(id_usuario);
    }

    @Override
    public void save(Monederos monedero) throws Exception {
        // COMO REGLA DE NEGOCIO, EL USUARIO SOLO PUEDE TENER 1 MONEDERO
        Monederos monederoExist = monederosRepository.findByIdUsuario(monedero.getIdUsuario());
        if (monederoExist != null) {
            throw new Exception("El usuario actual ya tiene un monedero creado. Máximo permitido: 1.");
        }
        int contador = findAll().toArray().length;
        monedero.setCodigo(contador + 1);
        monederosRepository.save(monedero);
    }

    @Override
    public void editPuntos(Long id, DTOUpdateMonederos updateMonedero) throws Exception {
        Monederos monederoFound = findById(id);
        usuariosService.findById(updateMonedero.getIdUsuario());
        monederoFound.setPuntos(updateMonedero.getPuntos());
        monederosRepository.save(monederoFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        monederosRepository.deleteById(id);
    }
}
