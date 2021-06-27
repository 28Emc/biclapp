package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateBicicletas;
import com.biclapp.model.DTO.DTOUpdate;
import com.biclapp.model.DTO.DTOUpdateBicicletas;
import com.biclapp.model.entity.Bicicletas;
import com.biclapp.model.entity.Locales;
import com.biclapp.model.entity.Usuarios;
import com.biclapp.repository.IBicicletasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BicicletasServiceImpl implements IBicicletasService {

    @Autowired
    private IBicicletasRepository bicicletasRepository;

    @Autowired
    private ILocalesService localesService;

    @Value("${gcp.img-product-default}")
    private String rutaFoto;

    @Value("${gcp.app-img-folder}")
    private String productImgfolder;

    @Autowired
    private GoogleCloudStorageService cloudStorageService;

    @Override
    @Transactional(readOnly = true)
    public List<Bicicletas> findAll() {
        //return (List<Bicicletas>) bicicletasRepository.findAll();
        List<String> estados = new ArrayList<>();
        estados.add("D");
        return bicicletasRepository.findByEstadoIn(estados);
    }

    @Override
    @Transactional(readOnly = true)
    public Bicicletas findById(Long id) throws Exception {
        return bicicletasRepository.findById(id).orElseThrow(() -> new Exception("La bicicleta con el id "
                .concat(id.toString()).concat(" no existe.")));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Bicicletas> findByMarcaAndModelo(String marca, String modelo) throws Exception {
        return bicicletasRepository.findByMarcaAndModelo(marca, modelo);
    }

    @Override
    @Transactional
    public void save(Bicicletas bicicleta) throws Exception {
        bicicletasRepository.save(bicicleta);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveCustom(DTOCreateBicicletas createBicicleta) throws Exception {
        localesService.findById(createBicicleta.getId_local());

        Optional<Bicicletas> bicicletaFound = bicicletasRepository.findByMarcaAndModelo(createBicicleta.getMarca(), createBicicleta.getModelo());
        if (bicicletaFound.isPresent()) {
            throw new Exception("La bicicleta con la marca ".concat(createBicicleta.getMarca().concat(" y el modelo").concat(createBicicleta.getModelo())
                    .concat(" ya existe.")));
        }

        int contador = findAll().toArray().length;

        if (createBicicleta.getFoto() != null) {
            String namePhoto = createBicicleta.getModelo().replace(" ", "-").toLowerCase();
            String path = productImgfolder.concat(namePhoto).concat(".jpg");
            rutaFoto = cloudStorageService.uploadImageToGCS(createBicicleta.getFoto(), path);
        }

        Bicicletas bicicletaNew = new Bicicletas(contador + 1, createBicicleta.getId_local(), createBicicleta.getMarca(),
                createBicicleta.getModelo(), createBicicleta.getStock(), createBicicleta.getDescripcion(), "D", rutaFoto,
                createBicicleta.getColor());
        bicicletasRepository.save(bicicletaNew);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(Long id, DTOUpdateBicicletas updateBicicleta) throws Exception {
        Bicicletas bicicletaFound = findById(id);
        Locales localFound = localesService.findById(updateBicicleta.getId_local());
        bicicletaFound.setId_local(localFound.getId());
        bicicletaFound.setCodigo(updateBicicleta.getCodigo());
        bicicletaFound.setMarca(updateBicicleta.getMarca());
        bicicletaFound.setModelo(updateBicicleta.getModelo());
        bicicletaFound.setStock(updateBicicleta.getStock());
        bicicletaFound.setDescripcion(updateBicicleta.getDescripcion());
        bicicletaFound.setFoto(updateBicicleta.getFoto());
        bicicletaFound.setColor(updateBicicleta.getColor());
        bicicletaFound.setEstado(updateBicicleta.getEstado());
        bicicletasRepository.save(bicicletaFound);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateEstado(Long id, DTOUpdate update) throws Exception {
        Bicicletas bicicletaFound = findById(id);
        bicicletaFound.setEstado(update.getEstado());
        bicicletasRepository.save(bicicletaFound);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updatePhotoBicicleta(Long id, MultipartFile photo) throws Exception {
        Bicicletas bicicletafound = findById(id);
        String namePhoto = bicicletafound.getModelo().replace(" ", "-").toLowerCase();
        String path = productImgfolder.concat(namePhoto).concat(".jpg");
        rutaFoto = cloudStorageService.uploadImageToGCS(photo, path);
        bicicletafound.setFoto(rutaFoto);
        bicicletasRepository.save(bicicletafound);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        findById(id);
        bicicletasRepository.deleteById(id);
    }
}
