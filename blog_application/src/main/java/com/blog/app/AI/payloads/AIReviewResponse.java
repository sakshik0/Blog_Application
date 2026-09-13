package com.blog.app.AI.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AIReviewResponse {
    private boolean success;
    private String message;
    private String review;
}
