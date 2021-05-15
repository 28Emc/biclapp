package com.biclapp.repository;

import com.biclapp.model.entity.Recorridos;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRecorridosRepository extends CrudRepository<Recorridos, Long> {

    List<Recorridos> findByIdUsuario(Long id_usuario);

    Recorridos findByIdAndIdUsuario(Long id, Long id_usuario);

}
