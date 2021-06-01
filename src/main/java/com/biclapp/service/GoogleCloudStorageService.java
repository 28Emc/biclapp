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
import java.util.Objects;

@Service
public class GoogleCloudStorageService {

    @Value("${gcp.bucket}")
    private String bucketName;

    @Value("${gcp.img-web-path}")
    private String imgWebPath;

    Credentials credentials = GoogleCredentials.fromStream(new ClassPathResource("gcp-credentials.json")
            .getInputStream());
    Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

    public GoogleCloudStorageService() throws IOException {
    }

    public String uploadImageToGCS(MultipartFile fileStream, String path)
            throws IOException, ServletException {
        String[] allowedExt = {"image/jpg", "image/jpeg", "image/png", "image/gif"};

        if (!this.isValidImage(allowedExt, fileStream)) {
            throw new ServletException("File must be an image");
        }

        storage.create(
                BlobInfo.newBuilder(bucketName, path).setContentType("image/jpg").build(),
                fileStream.getBytes()
        );

        return imgWebPath.concat(path);
    }

    public boolean isValidImage(String[] extensions, MultipartFile file) {
        for (String ext : extensions) {
            if (Objects.equals(file.getContentType(), ext)) {
                return true;
            }
        }
        return false;
    }
}
