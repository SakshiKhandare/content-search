package com.example.contentsearch.content.controller;

import com.example.contentsearch.content.dto.SearchContentResponse;
import com.example.contentsearch.content.entity.Content;
import com.example.contentsearch.content.service.TrendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class TrendingController {

    private final TrendingService trendingService;

    @GetMapping("/trending")
    public Page<SearchContentResponse> getTrending(
            @RequestParam(defaultValue = "7") int days,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<Content> trendingPage =
                trendingService.getTrending(days, page, size);

        return trendingPage.map(content ->
                SearchContentResponse.builder()
                        .id(content.getId())
                        .title(content.getTitle())
                        .category(content.getCategory())
                        .tags(content.getTags())
                        .publishedAt(content.getPublishedAt())
                        .views(content.getViews())
                        .relevanceScore(0) // not applicable for trending
                        .build()
        );
    }
}
