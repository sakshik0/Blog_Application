package com.blog.app.AI.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIReviewRequest {
    @NotBlank(message = "code must not be empty")
    @Size(max = 5000, message = "code must not exceed 5000 characters")
    private String code;

    @NotBlank(message = "code must not be empty")
    private String language;
}
