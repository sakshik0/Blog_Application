package com.blog.app.AI.controllers;

import com.blog.app.AI.payloads.AIReviewRequest;
import com.blog.app.AI.payloads.AIReviewResponse;
import com.blog.app.AI.services.AIService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ai")
@AllArgsConstructor
public class AIReviewController {

    AIService aiService;

    @PostMapping("/review")
    public ResponseEntity<AIReviewResponse> reviewCode(@Valid @RequestBody AIReviewRequest request) {

        AIReviewResponse aiReviewResponse = aiService.reviewCode(request);
        return new ResponseEntity<>(aiReviewResponse , HttpStatus.OK);
    }
}