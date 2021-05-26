package com.biclapp.controller;

import com.biclapp.service.GoogleCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class GCSController {

    @Autowired
    private GoogleCloudStorageService cloudStorageService;

    @PostMapping("/imageUpload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile fileStream, String username)
            throws IOException, ServletException {
        Map<String, Object> response = new HashMap<>();

        try {
            cloudStorageService.uploadImageToGCS(fileStream, username);
            response.put("message", "¡Imagen subida correctamente!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ServletException e) {
            response.put("message", "¡El archivo seleccionado debe ser una imagen!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}