package com.example.contentsearch.content.controller;

import com.example.contentsearch.content.dto.SearchContentRequest;
import com.example.contentsearch.content.dto.SearchContentResponse;
import com.example.contentsearch.content.entity.Content;
import com.example.contentsearch.content.service.SearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class SearchContentController {

    private final SearchService searchService;

    @GetMapping("/search")
    public Page<SearchContentResponse> search(
            @Valid @ModelAttribute SearchContentRequest request) {

        Sort sort = Sort.by(
                Sort.Direction.fromString(request.getSortDirection()),
                request.getSortBy()
        );

        PageRequest pageRequest =
                PageRequest.of(request.getPage(), request.getSize(), sort);

        Page<Content> rankedPage =
                searchService.searchWithRelevance(request, pageRequest);

        return rankedPage.map(content ->
                SearchContentResponse.builder()
                        .id(content.getId())
                        .title(content.getTitle())
                        .category(content.getCategory())
                        .tags(content.getTags())
                        .publishedAt(content.getPublishedAt())
                        .views(content.getViews())
                        .relevanceScore(
                                searchService
                                        .searchWithRelevance(request, pageRequest)
                                        .getContent()
                                        .stream()
                                        .filter(c -> c.getId().equals(content.getId()))
                                        .findFirst()
                                        .map(c -> 0) // placeholder, explained below
                                        .orElse(0)
                        )
                        .build()
        );
    }
}
