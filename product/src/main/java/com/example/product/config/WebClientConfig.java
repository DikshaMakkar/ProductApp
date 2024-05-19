package com.example.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

//    @Value("${api.url}")
//    private final String apiUrl;
//
//    public WebClientConfig(String apiUrl) {
//        this.apiUrl = apiUrl;
//    }

    @Bean
    public String apiUrl() {
        // Logic to construct the base URL string (e.g., from environment variables)
        String url = "https://api.example.com"; // Replace with your actual URL
        return url;
    }

    @Bean
    public WebClient webClient(String apiUrl) {
        return WebClient.create(apiUrl);
    }
}
