package com.berkaycetin.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.berkaycetin")
@EnableJpaRepositories(basePackages = "com.berkaycetin.repository")
@EntityScan(basePackages = "com.berkaycetin.entities")
public class TakManager2Application {

    public static void main(String[] args) {
        SpringApplication.run(TakManager2Application.class, args);
    }

}
