package com.biclapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BiclappApplication {
    public static void main(String[] args) {
        SpringApplication.run(BiclappApplication.class, args);
    }
}
