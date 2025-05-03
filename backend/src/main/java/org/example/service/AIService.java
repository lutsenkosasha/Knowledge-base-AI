package org.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIService {
    private final RestTemplate restTemplate;

    @Value("${openrouter.api.url}")
    private String apiUrl;

    @Value("${openrouter.api.key}")
    private String apiKey;

    public String getAIResponse(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("HTTP-Referer", "http://localhost:8080");
        headers.set("X-Title", "KnowledgeBaseAI");
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = Map.of(
                "model", "deepseek/deepseek-r1:free",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                ),
                "temperature", 0.7,
                "max_tokens", 1000
        );

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(request, headers),
                    Map.class
            );

            return extractContent(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException("AI Service error: " + e.getMessage());
        }
    }

    private String extractContent(Map<String, Object> response) {
        try {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
            Map<String, Object> firstChoice = choices.get(0);
            Map<String, Object> message = (Map<String, Object>) firstChoice.get("message");
            return (String) message.get("content");
        } catch (Exception e) {
            throw new RuntimeException("Invalid response format from AI", e);
        }
    }
}

