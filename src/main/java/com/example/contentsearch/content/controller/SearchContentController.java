package com.example.contentsearch.content.controller;

import com.example.contentsearch.content.dto.SearchContentRequest;
import com.example.contentsearch.content.dto.SearchContentResponse;
import com.example.contentsearch.content.entity.Content;
import com.example.contentsearch.content.repository.ContentRepository;
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

    private final ContentRepository contentRepository;

    @GetMapping("/search")
    public Page<SearchContentResponse> search(
            @Valid @ModelAttribute SearchContentRequest request) {

        Sort sort = Sort.by(
                Sort.Direction.fromString(request.getSortDirection()),
                request.getSortBy()
        );

        PageRequest pageRequest =
                PageRequest.of(request.getPage(), request.getSize(), sort);

        Page<Content> resultPage =
                contentRepository.searchByKeyword(
                        request.getKeyword(),
                        pageRequest
                );

        return resultPage.map(this::toResponse);
    }

    private SearchContentResponse toResponse(Content content) {
        return SearchContentResponse.builder()
                .id(content.getId())
                .title(content.getTitle())
                .category(content.getCategory())
                .tags(content.getTags())
                .publishedAt(content.getPublishedAt())
                .views(content.getViews())
                .build();
    }
}
