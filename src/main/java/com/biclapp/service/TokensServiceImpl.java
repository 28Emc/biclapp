package com.biclapp.service;

import com.biclapp.config.mail.EmailService;
import com.biclapp.model.entity.Tokens;
import com.biclapp.model.entity.Usuarios;
import com.biclapp.repository.ITokensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokensServiceImpl implements ITokensService {

    @Autowired
    private ITokensRepository tokensRepository;

    @Autowired
    private IUsuariosService usuarioService;

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    @Transactional(readOnly = true)
    public List<Tokens> findByEmail(String email) {
        return tokensRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Tokens findByEmailAndCodigo(String email, int codigo) throws Exception {
        return tokensRepository.findByEmailAndCodigo(email, codigo).orElseThrow(() -> new Exception("El código no se encuenta o ya expiró."));
    }

    @Override
    public void save(Tokens token) {
        tokensRepository.save(token);
    }

    @Override
    public int updateToken(String email, String tipoOperacion) throws Exception {
        Tokens tokenFound = tokensRepository.findByEmailAndTipoAccion(email, tipoOperacion);
        Usuarios usuarioFound = usuarioService.findByUsername(email);
        int codigoRandom = (int) Math.floor(Math.random() * (1 - 9999 + 1) + 9999);
        String codStr = String.valueOf(codigoRandom);

        while (codStr.length() < 4) {
            codStr = codStr.concat("0");
        }

        codigoRandom = Integer.parseInt(codStr);
        tokenFound.setCodigo(codigoRandom);
        tokensRepository.save(tokenFound);

        Map<String, Object> model = new HashMap<>();
        model.put("from", emailFrom);
        model.put("to", usuarioFound.getUsername());
        model.put("subject", "Biclapp - Nuevo código de verificación");
        model.put("titulo-cabecera", "Reenvío código");
        model.put("codigo-verificacion", tokenFound.getCodigo());

        emailService.enviarEmail(model, "NUEVO CÓDIGO");
        return tokenFound.getCodigo();
    }

    @Override
    public void delete(Long id) {
        tokensRepository.deleteById(id);
    }
}
