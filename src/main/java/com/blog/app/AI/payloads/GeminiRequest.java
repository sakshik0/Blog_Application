package com.blog.app.AI.payloads;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GeminiRequest {

    private SystemInstruction systemInstruction;
    private List<Content> contents;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class SystemInstruction {
        private List<Part> parts;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class Content {
        private List<Part> parts;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class Part {
        private String text;
    }
}