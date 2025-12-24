package com.example.contentsearch.content.controller;

import com.example.contentsearch.content.dto.SearchContentResponse;
import com.example.contentsearch.content.entity.Content;
import com.example.contentsearch.content.service.ContentReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentReadController {

    private final ContentReadService contentReadService;

    @GetMapping("/{id}")
    public SearchContentResponse read(@PathVariable Long id) {
        Content content = contentReadService.readAndIncrementViews(id);

        return SearchContentResponse.builder()
                .id(content.getId())
                .title(content.getTitle())
                .category(content.getCategory())
                .tags(content.getTags())
                .publishedAt(content.getPublishedAt())
                .views(content.getViews())
                .relevanceScore(0) // not applicable for single read
                .build();
    }
}
