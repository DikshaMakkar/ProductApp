package com.example.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DemoService {

    @Autowired
    private WebClient webClient;

    @Value("${api.url}")
    private final String apiUrl;

    @Value("${api.token}")
    private final String apiToken;

    public DemoService(String apiUrl, String apiToken) {
        this.apiUrl = apiUrl;
        this.apiToken = apiToken;
    }

    public String callApi(String path) {
        String url = apiUrl + path;
        String token = apiToken;
        WebClient.Builder builder = WebClient.builder();

        Mono<String> responseMono = builder.build()
                .get()
                .uri(url)
                .header("Authorization", "Bearer " + apiToken)
                .retrieve()
                .bodyToMono(String.class);

        String response = responseMono.block(); // Blocks until response is received (consider using asynchronous approach for non-blocking)
        return response;
    }
}
