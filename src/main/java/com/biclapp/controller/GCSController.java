package com.biclapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;

@RestController
public class GCSController {

    @Value("gs://spring-bucket-biclapp-dev/my-file.txt")
    private Resource gcsFile;

    @GetMapping("/storage")
    public String getFileFromGCS() throws IOException {
        return StreamUtils.copyToString(
                this.gcsFile.getInputStream(),
                Charset.defaultCharset()
        );
    }
}
