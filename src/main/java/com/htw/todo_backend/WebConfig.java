package com.htw.todo_backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration: sagt Spring Boot, dass diese Klasse Konfiguration enthält
// @EnableWebMvc: aktiviert die Web-MVC-Konfiguration
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // CORS-Konfiguration: erlaubt dem Frontend auf das Backend zuzugreifen
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")           // alle Pfade erlauben
                .allowedMethods("*")          // alle HTTP-Methoden (GET, POST, PUT, DELETE)
                .allowedOrigins(
                    "http://localhost:5173",   // lokales Vue-Frontend
                    "http://localhost:3000"    // alternative Ports
                );
    }
}
