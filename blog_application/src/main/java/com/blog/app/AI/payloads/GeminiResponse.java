package com.blog.app.AI.payloads;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class GeminiResponse {

    private List<Candidate> candidates;

    @Getter @Setter @NoArgsConstructor
    public static class Candidate {
        private ContentBlock content;
    }

    @Getter @Setter @NoArgsConstructor
    public static class ContentBlock {
        private List<PartBlock> parts;
    }

    @Getter @Setter @NoArgsConstructor
    public static class PartBlock {
        private String text;
    }
}