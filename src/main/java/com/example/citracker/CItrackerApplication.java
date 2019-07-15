package com.example.citracker;

import com.example.citracker.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class CItrackerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CItrackerApplication.class, args);
  }
}
