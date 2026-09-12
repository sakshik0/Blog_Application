package com.blog.app.AI.services;

import com.blog.app.AI.exceptions.AiServiceUnavailableException;
import com.blog.app.AI.payloads.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final RestClient geminiRestClient;

    @Value("${gemini.api.model}")
    private String model;

    /**private static final String SYSTEM_PROMPT = """
            You are a strict senior Java/Spring Boot code reviewer.
            Review the given code and respond ONLY in this exact structure:
            1. Naming issues (classes, methods, variables, constants)
            2. Complexity (time/space complexity, unnecessary loops, better algorithms)
            3. Clean Code (duplication, long methods, unnecessary conditions, SOLID violations)
            4. Spring Boot practices (dependency injection, layering, exception handling, DTOs, JPA)
            5. Security (hardcoded secrets, SQL injection, unsafe input, data exposure)
            6. Suggested Fixes (concrete code snippets)
            If a category has no issues, say "No issues found" for that category.
            """;**/
    private static final String SYSTEM_PROMPT = """
        You are a strict senior Java/Spring Boot code reviewer.
        Review the given code and respond ONLY in this exact structure:
        1. Naming issues (classes, methods, variables, constants)
        2. Complexity (time/space complexity, unnecessary loops, better algorithms)
        3. Clean Code (duplication, long methods, unnecessary conditions, SOLID violations)
        4. Spring Boot practices (dependency injection, layering, exception handling, DTOs, JPA)
        5. Security (hardcoded secrets, SQL injection, unsafe input, data exposure)
        6. Suggested Fixes (concrete code snippets)

        Rules for Suggested Fixes:
        - Fix ONLY the specific issues you identified above — do not restructure,
          rename, or redesign anything you did not flag as a problem.
        - Keep the original class/method structure and package name intact unless
          the structure itself is the issue.
        - Show fixes as small, targeted snippets (the changed lines/method only),
          not a full rewritten class, unless the whole class is the actual issue.
        - If a category has no issues, say "No issues found" for that category.
        """;

    @Retryable(
            retryFor = AiServiceUnavailableException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    @Override
    public AIReviewResponse reviewCode(AIReviewRequest request) {

        GeminiRequest payload = GeminiRequest.builder()
                .systemInstruction(new GeminiRequest.SystemInstruction(
                        List.of(new GeminiRequest.Part(SYSTEM_PROMPT))
                ))
                .contents(List.of(
                        new GeminiRequest.Content(List.of(
                                new GeminiRequest.Part(
                                        "Language: " + request.getLanguage() + "\n\nCode:\n" + request.getCode()
                                )
                        ))
                ))
                .build();

        try {
            GeminiResponse response = geminiRestClient.post()
                    .uri("/models/{model}:generateContent", model)
                    .body(payload)
                    .retrieve()
                    .body(GeminiResponse.class);

            if (response == null || response.getCandidates() == null || response.getCandidates().isEmpty()) {
                return new AIReviewResponse(false, "AI service returned no result", null);
            }

            String review = response.getCandidates().get(0).getContent().getParts().get(0).getText();
            return new AIReviewResponse(true, "Code review completed", review);

        } catch (RestClientResponseException ex) {
            return new AIReviewResponse(
                    false,
                    "AI service error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString(),
                    null
            );
        } catch (Exception ex) {
            return new AIReviewResponse(false, "Unexpected error: " + ex.getMessage(), null);
        }
    }
    @Recover
    public AIReviewResponse recover(AiServiceUnavailableException ex, AIReviewRequest request) {
        return new AIReviewResponse(
                false,
                "Gemini is currently overloaded after multiple retry attempts. Please try again in a bit.",
                null
        );
    }
}