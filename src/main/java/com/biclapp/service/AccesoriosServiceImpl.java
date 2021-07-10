package com.biclapp.service;

import com.biclapp.model.DTO.DTOUpdateAccesorios;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.entity.Accesorios;
import com.biclapp.model.entity.Bicicletas;
import com.biclapp.repository.IAccesoriosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccesoriosServiceImpl implements IAccesoriosService {

    @Autowired
    private IAccesoriosRepository repository;

    @Value("${gcp.img-product-default}")
    private String rutaFoto;

    @Value("${gcp.app-img-folder}")
    private String productImgfolder;

    @Autowired
    private GoogleCloudStorageService cloudStorageService;

    @Override
    @Transactional(readOnly = true)
    public List<Accesorios> findAll() {
        return (List<Accesorios>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Accesorios> findAllByEstado() {
        List<String> estados = new ArrayList<>();
        estados.add("D");
        estados.add("N");
        return repository.findByEstadoIn(estados);
    }

    @Override
    @Transactional(readOnly = true)
    public Accesorios findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(
                () -> new Exception("El accesorio con id ".concat(id.toString()).concat(" no existe."))
        );
    }

    @Override
    public void save(Accesorios accesorio) throws Exception {
        int contador = findAll().toArray().length;
        accesorio.setCodigo(contador + 1);
        accesorio.setEstado("D");
        repository.save(accesorio);
    }

    @Override
    public void update(Long id, DTOUpdateAccesorios createAccesorio) throws Exception {
        Accesorios accesorioFound = findById(id);
        accesorioFound.setCodigo(createAccesorio.getCodigo());
        accesorioFound.setNombre(createAccesorio.getNombre());
        accesorioFound.setDescripcion(createAccesorio.getDescripcion());
        accesorioFound.setFoto(createAccesorio.getFoto());
        accesorioFound.setTipo(createAccesorio.getTipo());
        accesorioFound.setStock(createAccesorio.getStock());
        accesorioFound.setPrecio(createAccesorio.getPrecio());
        accesorioFound.setEstado(createAccesorio.getEstado());
        repository.save(accesorioFound);
    }

    @Override
    public void updateEstado(Long id, DTOUpdate update) throws Exception {
        Accesorios accesorioFound = findById(id);
        accesorioFound.setEstado((update.getEstado()));
        repository.save(accesorioFound);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updatePhotoAccesorio(Long id, MultipartFile photo) throws Exception {
        Accesorios accesorioFound = findById(id);
        String namePhoto = "accesorio-".concat(accesorioFound.getId().toString().replace(" ", "-").toLowerCase());
        String path = productImgfolder.concat(namePhoto).concat(".jpg");
        rutaFoto = cloudStorageService.uploadImageToGCS(photo, path);
        accesorioFound.setFoto(rutaFoto);
        repository.save(accesorioFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }
}
