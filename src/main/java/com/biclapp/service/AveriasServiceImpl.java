package com.biclapp.service;

import com.biclapp.config.mail.EmailService;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateAverias;
import com.biclapp.model.entity.Averias;
import com.biclapp.model.entity.Usuarios;
import com.biclapp.repository.IAveriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AveriasServiceImpl implements IAveriasService {

    @Autowired
    private IAveriasRepository averiasRepository;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String emailFrom;

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
    @Transactional(readOnly = true)
    public List<Averias> findByIdUsuario(Long idUsuario) throws Exception {
        return averiasRepository.findByIdUsuario(idUsuario);
    }

    @Override
    public void save(Averias averia) throws Exception {
        Usuarios usuarioFound = usuariosService.findById(averia.getIdUsuario());
        int contador = findAll().toArray().length;
        averia.setCodigo(contador + 1);
        averia.setEstado("P");
        averiasRepository.save(averia);

        Map<String, Object> model = new HashMap<>();
        model.put("from", emailFrom);
        model.put("to", usuarioFound.getUsername());
        model.put("subject", "Biclapp - Avería registrada");
        model.put("titulo-cabecera", "Avería registrada");
        model.put("averia", averia);
        emailService.enviarEmail(model, "REGISTRO AVERIA");
    }

    @Override
    public void update(Long id, DTOUpdateAverias updateAveria) throws Exception {
        Averias averiaFound = findById(id);
        Usuarios usuarioFound = usuariosService.findById(averiaFound.getIdUsuario());
        averiaFound.setIdUsuario(usuarioFound.getId());
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
