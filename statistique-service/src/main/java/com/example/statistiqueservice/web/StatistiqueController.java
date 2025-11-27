package com.example.statistiqueservice.web;

import com.example.statistiqueservice.service.YouTubeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class StatistiqueController {

    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
    private final YouTubeService youTubeService;

    public StatistiqueController(YouTubeService youTubeService) {
        this.youTubeService = youTubeService;
    }

    @GetMapping("/stats/youtube")
    public Mono<String> getYouTubeStats(@RequestParam(defaultValue = "Spring Boot") String query,
            @RequestParam(defaultValue = "10") int maxResults) {
        return youTubeService.searchVideos(query, maxResults);
    }

    @GetMapping(value = "/stats/youtube/view", produces = "text/html")
    public Mono<String> viewYouTubeStats(@RequestParam(defaultValue = "Spring Boot") String query,
            @RequestParam(defaultValue = "10") int maxResults) {
        return youTubeService.searchVideos(query, maxResults)
                .map(json -> {
                    StringBuilder html = new StringBuilder();
                    html.append("<html><body style='font-family: Arial; text-align: center;'>");
                    html.append("<h1>Résultats pour : " + query + "</h1>");
                    html.append("<div style='display: flex; flex-wrap: wrap; justify-content: center; gap: 20px;'>");

                    try {
                        com.fasterxml.jackson.databind.JsonNode root = objectMapper.readTree(json);
                        com.fasterxml.jackson.databind.JsonNode items = root.path("items");
                        if (items.isArray()) {
                            for (com.fasterxml.jackson.databind.JsonNode item : items) {
                                String videoId = item.path("id").path("videoId").asText();
                                String title = item.path("snippet").path("title").asText();
                                if (videoId != null && !videoId.isEmpty()) {
                                    html.append(
                                            "<div style='border: 1px solid #ddd; padding: 10px; border-radius: 8px; width: 340px;'>");
                                    html.append("<h3>").append(title).append("</h3>");
                                    html.append("<iframe width='320' height='180' src='https://www.youtube.com/embed/")
                                            .append(videoId).append("' frameborder='0' allowfullscreen></iframe>");
                                    html.append("</div>");
                                }
                            }
                        }
                    } catch (Exception e) {
                        html.append("<p>Erreur lors du parsing JSON: " + e.getMessage() + "</p>");
                    }

                    html.append("</div></body></html>");
                    return html.toString();
                });
    }
}
