package com.biclapp.service;

import com.biclapp.model.entity.TiposPago;
import com.biclapp.repository.ITiposPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TiposPagoServiceImpl implements ITiposPagoService {

    @Autowired
    private ITiposPagoRepository tiposPagoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TiposPago> findAll() {
        return (List<TiposPago>) tiposPagoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TiposPago findById(Long id) throws Exception {
        return tiposPagoRepository.findById(id).orElseThrow(
                () -> new Exception("El tipo de pago con id ".concat(id.toString()).concat(" no existe."))
        );
    }

    @Override
    public void save(TiposPago tipoPago) throws Exception {
        tiposPagoRepository.save(tipoPago);
    }

    @Override
    public void update(Long id, TiposPago tipoPago) throws Exception {
        TiposPago tipoPagoFound = findById(id);
        tipoPagoFound.setTipo(tipoPago.getTipo());
        tiposPagoRepository.save(tipoPagoFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        tiposPagoRepository.deleteById(id);
    }
}
