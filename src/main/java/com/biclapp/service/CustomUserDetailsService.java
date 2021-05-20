package com.biclapp.service;

import com.biclapp.config.security.CustomUserDetails;
import com.biclapp.model.entity.Usuarios;
import com.biclapp.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuarios> usuarioFound = usuariosRepository.findByUsername(username);
        usuarioFound.orElseThrow(() -> new UsernameNotFoundException("¡Usuario ".concat(username).concat(" no encontrado!")));
        return usuarioFound.map(CustomUserDetails::new).get();
    }
}
