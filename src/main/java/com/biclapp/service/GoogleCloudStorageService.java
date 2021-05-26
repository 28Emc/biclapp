package com.biclapp.service;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import java.io.IOException;

@Service
public class GoogleCloudStorageService {

    @Value("${gcp.bucket}")
    private String bucketName;

    @Value("${gcp.app-img-folder}")
    private String appImgfolder;

    @Value("${gcp.user-img-folder}")
    private String userImgfolder;

    @Value("${gcp.img-web-path}")
    private String urlWebPath;

    Credentials credentials = GoogleCredentials.fromStream(new ClassPathResource("gcp-credentials.json")
            .getInputStream());
    Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

    public GoogleCloudStorageService() throws IOException {
    }

    public String uploadImageToGCS(MultipartFile fileStream, String username)
            throws IOException, ServletException {
        String[] allowedExt = {".jpg", ".jpeg", ".png", ".gif"};

        for (String ext : allowedExt) {
            if (!fileStream.getName().endsWith(ext)) {
                throw new ServletException("File must be an image");
            }
        }

        final String fileName = userImgfolder.concat(username).concat(".jpg");
        storage.create(
                BlobInfo.newBuilder(bucketName, fileName).setContentType("image/jpg").build(),
                fileStream.getBytes()
        );

        System.out.println("Ruta final: ");
        System.out.println(urlWebPath.concat(fileName));
        return urlWebPath.concat(fileName);
    }
}
