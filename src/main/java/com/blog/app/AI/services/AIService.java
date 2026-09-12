package com.blog.app.AI.services;

import com.blog.app.AI.payloads.AIReviewRequest;
import com.blog.app.AI.payloads.AIReviewResponse;

public interface AIService {
    AIReviewResponse reviewCode(AIReviewRequest request);
}
