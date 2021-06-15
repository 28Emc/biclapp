package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateMonederos;
import com.biclapp.model.DTO.DTOUpdateRecorridos;
import com.biclapp.model.entity.Monederos;
import com.biclapp.model.entity.Recorridos;
import com.biclapp.model.entity.Usuarios;
import com.biclapp.repository.IRecorridosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RecorridosServiceImpl implements IRecorridosService {

    @Autowired
    private IRecorridosRepository recorridosRepository;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private IMonederosService monederoService;

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
        recorrido.setFecha_registro(LocalDateTime.now());
        recorrido.setKilometros(0.00);
        recorrido.setKcal(0.00);
        recorrido.setHoras(0);
        recorrido.setMinutos(0);
        recorridosRepository.save(recorrido);
    }

    @Override
    public void update(Long id, DTOUpdateRecorridos updateRecorrido) throws Exception {
        Recorridos recorridoFound = findById(id);
        Usuarios usuarioFound = usuariosService.findById(updateRecorrido.getIdUsuario());
        recorridoFound.setKilometros(updateRecorrido.getKilometros());
        recorridoFound.setFecha_actualizacion(LocalDateTime.now());
        recorridoFound.setEstado(updateRecorrido.getEstado());

        if (updateRecorrido.getEstado().equals("C") || updateRecorrido.getEstado().equals("B")) {
            realizarCalculosRecorrido(recorridoFound, Double.parseDouble(usuarioFound.getPeso()));
        } else {
            recorridosRepository.save(recorridoFound);
        }
    }

    @Override
    public void updateEstado(Long id, DTOUpdate update) throws Exception {
        Recorridos recorridoFound = findById(id);
        recorridoFound.setEstado(update.getEstado());
        recorridosRepository.save(recorridoFound);
    }

    public void realizarCalculosRecorrido(Recorridos recorrido, Double peso) throws Exception {
        double kcalFinales;
        final double CONST_FORMULA_1 = 0.049;
        final double CONST_FORMULA_2 = 2.2;
        long hours;
        long minutes;
        minutes = ChronoUnit.MINUTES.between(recorrido.getFecha_registro(), recorrido.getFecha_actualizacion());
        recorrido.setMinutos((int) minutes);

        hours = ChronoUnit.HOURS.between(recorrido.getFecha_registro(), recorrido.getFecha_actualizacion());
        recorrido.setHoras((int) hours);

        kcalFinales = CONST_FORMULA_1 * (peso * CONST_FORMULA_2) * recorrido.getMinutos();
        recorrido.setKcal(kcalFinales);

        if (recorrido.getEstado().equals("C") && recorrido.getKilometros() > 0.00 && minutes > 0) {
            asignarPuntosRecorrido(recorrido);
        }

        recorridosRepository.save(recorrido);
    }

    public void asignarPuntosRecorrido(Recorridos recorrido) throws Exception {
        double puntosCalculados;
        Monederos monederoFound = monederoService.findByUser(recorrido.getIdUsuario());
        puntosCalculados = 300 * recorrido.getKilometros();
        monederoService.editPuntos(monederoFound.getId(),
                new DTOUpdateMonederos(recorrido.getIdUsuario(), monederoFound.getPuntos() + (int) puntosCalculados));
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        recorridosRepository.deleteById(id);
    }
}

