package com.example.twiiterapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TwiiterapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwiiterapiApplication.class, args);
    }

    // CORS ayarı
    @Bean
    public WebMvcConfigurer corsConfigurer2() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // tüm endpointler
                        .allowedOrigins("http://localhost:3200") // React portu
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // izinli HTTP metodları
                        .allowCredentials(true);
            }
        };
    }
}