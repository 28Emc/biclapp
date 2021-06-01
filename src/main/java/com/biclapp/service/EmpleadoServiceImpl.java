package com.biclapp.service;

import com.biclapp.model.DTO.DTOCreateEmpleados;
import com.biclapp.model.entity.Empleados;
import com.biclapp.model.entity.Locales;
import com.biclapp.model.entity.Roles;
import com.biclapp.repository.IEmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements IEmpleadosService {

    @Autowired
    private IEmpleadosRepository empleadosRepository;

    @Autowired
    private IRolesService rolesService;

    @Autowired
    private ILocalesService localService;

    @Autowired
    private GoogleCloudStorageService cloudStorageService;

    @Value("${gcp.img-employee-default}")
    private String rutaFoto;

    @Value("${gcp.employee-img-folder}")
    private String employeeImgfolder;

    @Override
    @Transactional(readOnly = true)
    public List<Empleados> findAll() {
        return (List<Empleados>) empleadosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empleados findById(Long id) throws Exception {
        return empleadosRepository.findById(id).orElseThrow(() -> new Exception("El empleado con el id ".concat(id.toString()).concat(" no existe.")));
    }

    @Override
    public void save(DTOCreateEmpleados createEmpleados) throws Exception {
        Roles rolFound = rolesService.findByRol("ROLE_EMPLEADO");
        Locales localFound = localService.findById(createEmpleados.getId_local());
        int contador = findAll().toArray().length;

        if (createEmpleados.getFoto() != null) {
            String namePhoto = createEmpleados.getUsername().split("@")[0].replace(" ", "-").toLowerCase();
            String path = employeeImgfolder.concat(namePhoto).concat(".jpg");
            rutaFoto = cloudStorageService.uploadImageToGCS(createEmpleados.getFoto(), path);
        }

        Empleados empleadosNew = new Empleados(contador + 1, rolFound, localFound.getId(), createEmpleados.getNombres(),
                createEmpleados.getApellidos(), createEmpleados.getNro_documento(), createEmpleados.getCelular(),
                createEmpleados.getDireccion(), createEmpleados.getUsername(), createEmpleados.getPassword(),
                createEmpleados.getEstado(), rutaFoto, true);
        empleadosRepository.save(empleadosNew);
    }

    @Override
    public void update(Long id, DTOCreateEmpleados createEmpleados) throws Exception {
        Empleados empleadoFound = findById(id);
        Locales localFound = localService.findById(createEmpleados.getId_local());
        empleadoFound.setCodigo(empleadoFound.getCodigo());
        empleadoFound.setId_local(localFound.getId());
        empleadoFound.setNombres(createEmpleados.getNombres());
        empleadoFound.setApellidos(createEmpleados.getApellidos());
        empleadoFound.setNro_documento(createEmpleados.getNro_documento());
        empleadoFound.setCelular(createEmpleados.getCelular());
        empleadoFound.setDireccion(createEmpleados.getDireccion());
        empleadoFound.setUsername(createEmpleados.getUsername());
        empleadoFound.setPassword(empleadoFound.getPassword());
        empleadoFound.setEstado(empleadoFound.getEstado());
        empleadoFound.setFoto(empleadoFound.getFoto());
        empleadoFound.setActivo(true);
        empleadosRepository.save(empleadoFound);
    }

    @Override
    public void updatePhotoEmpleado(Long id, MultipartFile photo) throws Exception {
        Empleados empleadoFound = findById(id);
        String namePhoto = empleadoFound.getUsername().split("@")[0].replace(" ", "-").toLowerCase();
        String path = employeeImgfolder.concat(namePhoto).concat(".jpg");
        rutaFoto = cloudStorageService.uploadImageToGCS(photo, path);
        empleadoFound.setFoto(rutaFoto);
        empleadosRepository.save(empleadoFound);
    }

    @Override
    public void delete(Long id) throws Exception {
        findById(id);
        empleadosRepository.deleteById(id);
    }
}
