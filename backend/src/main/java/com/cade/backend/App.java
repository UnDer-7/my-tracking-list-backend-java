package com.cade.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
@EntityScan(basePackages = "com.cade")
@ComponentScan(basePackages = "com.cade")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
