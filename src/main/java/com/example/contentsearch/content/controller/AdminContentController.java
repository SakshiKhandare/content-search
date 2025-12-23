package com.example.contentsearch.content.controller;


import com.example.contentsearch.content.dto.CreateContentRequest;
import com.example.contentsearch.content.dto.CreateContentResponse;
import com.example.contentsearch.content.entity.Content;
import com.example.contentsearch.content.repository.ContentRepository;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin/contents")
@RequiredArgsConstructor
public class AdminContentController {

    private final ContentRepository contentRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateContentResponse createContent(@Valid @RequestBody CreateContentRequest request) {

        Content content = Content.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .category(request.getCategory())
                .tags(request.getTags())
                .status("PUBLISHED")
                .publishedAt(LocalDateTime.now())
                .views(0L)
                .build();

        Content saved = contentRepository.save(content);

        return CreateContentResponse.builder()
                .id(saved.getId())
                .status(saved.getStatus())
                .publishedAt(saved.getPublishedAt())
                .build();
    }
}
