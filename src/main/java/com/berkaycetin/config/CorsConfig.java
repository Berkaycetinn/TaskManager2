package com.berkaycetin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // React frontend adresi
        config.setAllowedOrigins(List.of("http://localhost:3000"));

        // izin verilen HTTP metodları
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // tüm headerlara izin ver
        config.setAllowedHeaders(List.of("*"));

        // JWT Authorization header kullanıyorsak gerekli
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source; // ✅ UrlBasedCorsConfigurationSource implement ediyor, tip uyumlu
    }
}



