package com.example.contentsearch.content.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
public class SearchContentResponse {

    private final Long id;
    private final String title;
    private final String category;
    private final Set<String> tags;
    private final LocalDateTime publishedAt;
    private final Long views;
    private final int relevanceScore;
}
