package com.cade.backend;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
    info = @Info(
        title="My Tracking List API",
        version = "1.0.0-SNAPSHOT",
        contact = @Contact(
            name = "Mateus Gomes",
            url = "https://www.linkedin.com/in/mateus-gomes-da-silva-cardoso/",
            email = "mateus7532@gmail.com"),
        license = @License(name = "License - ToDo"))
)
@Slf4j
@QuarkusMain
public class App extends Application {
    public static void main(String ... args) {
        log.info("Running main method");
        Quarkus.run(args);
    }
}
