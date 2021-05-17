package com.biclapp.service;

import com.biclapp.model.entity.Empresas;
import com.biclapp.model.entity.Locales;
import com.biclapp.repository.ILocalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocalesServiceImpl implements ILocalesService {

    @Autowired
    private ILocalesRepository repository;

    @Autowired
    private IEmpresaService empresaService;

    @Override
    @Transactional(readOnly = true)
    public List<Locales> findAll() {
        return (List<Locales>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Locales> findByEmpresa(Long id_empresa) throws Exception {
        empresaService.findById(id_empresa);
        return repository.findByIdEmpresa(id_empresa);
    }

    @Override
    @Transactional(readOnly = true)
    public Locales findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("El local con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    public void save(Locales local) throws Exception {
        int contador = findAll().toArray().length;
        local.setCodigo(contador + 1);
        repository.save(local);
    }

    @Override
    public void update(Long id, Locales local) throws Exception {
        Locales localFound = findById(id);
        empresaService.findById(local.getIdEmpresa());
        localFound.setDireccion(local.getDireccion());
        repository.save(localFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
