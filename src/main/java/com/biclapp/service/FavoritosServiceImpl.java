package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdateFavoritos;
import com.biclapp.model.entity.Favoritos;
import com.biclapp.model.entity.Usuarios;
import com.biclapp.repository.IFavoritosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoritosServiceImpl implements IFavoritosService {

    @Autowired
    private IFavoritosRepository favoritosRepository;

    @Autowired
    private IUsuariosService usuariosService;

    @Override
    @Transactional(readOnly = true)
    public List<Favoritos> findAll() {
        return (List<Favoritos>) favoritosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Favoritos> findByUser(Long id_usuario) throws Exception {
        usuariosService.findById(id_usuario);
        return favoritosRepository.findByIdUsuario(id_usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Favoritos findById(Long id) throws Exception {
        return favoritosRepository.findById(id).orElseThrow(
                () -> new Exception("El favorito con id ".concat(id.toString()).concat(" no existe."))
        );
    }

    @Override
    public void save(Favoritos favorito) throws Exception {
        favoritosRepository.save(favorito);
    }

    @Override
    public void update(Long id, DTOUpdateFavoritos updateFavorito) throws Exception {
        Favoritos favoritoFound = findById(id);
        Usuarios usuarioFound = usuariosService.findById(updateFavorito.getId_usuario());
        favoritoFound.setIdUsuario(usuarioFound.getId());
        favoritoFound.setTipo_destino(updateFavorito.getTipo_destino());
        favoritoFound.setNombre_coordenadas(updateFavorito.getNombre_coordenadas());
        favoritoFound.setDescripcion(updateFavorito.getDescripcion());
        favoritosRepository.save(favoritoFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        favoritosRepository.deleteById(id);
    }
}
