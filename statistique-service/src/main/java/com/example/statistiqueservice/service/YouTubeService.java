package com.example.statistiqueservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class YouTubeService {

    private final WebClient webClient;

    @Value("${youtube.api.key}")
    private String apiKey;

    @Value("${youtube.api.url}")
    private String apiUrl;

    public YouTubeService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    // Exemple : Récupérer les stats d'une vidéo ou une recherche
    // Ici, on fait une recherche simple pour l'exemple
    public Mono<String> searchVideos(String query, int maxResults) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("www.googleapis.com")
                        .path("/youtube/v3/search")
                        .queryParam("part", "snippet")
                        .queryParam("q", query)
                        .queryParam("maxResults", maxResults)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
