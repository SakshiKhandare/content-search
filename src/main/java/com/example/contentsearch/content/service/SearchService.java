package com.example.contentsearch.content.service;

import com.example.contentsearch.content.dto.SearchContentRequest;
import com.example.contentsearch.content.entity.Content;
import com.example.contentsearch.content.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ContentRepository contentRepository;

    public Page<Content> searchWithRelevance(
            SearchContentRequest request,
            Pageable pageable
    ){

        Page<Content> page = contentRepository.search(
                request.getKeyword(),
                request.getCategory(),
                request.getTags(),
                pageable
        );

        List<Content> ranked = page.getContent().stream()
                .sorted(Comparator
                        .comparingInt((Content c) ->
                                computeRelevanceScore(c, request))
                        .reversed()
                        .thenComparing(Content::getPublishedAt,
                                Comparator.nullsFirst(Comparator.reverseOrder()))

                )
                .toList();

        return new PageImpl<>(ranked, pageable, page.getTotalElements());
    }

    private int computeRelevanceScore(Content content, SearchContentRequest request) {

        int score = 0;
        String keyword = request.getKeyword().toLowerCase();

        // Rule 1: keyword in title
        if(content.getTitle() != null &&
            content.getTitle().toLowerCase().contains(keyword)){
            score += 5;
        }

        // Rule 2: keyword in body
        if(content.getBody() != null &&
                content.getBody().toLowerCase().contains(keyword)){
            score += 2;
        }

        // Rule 3: category match
        if(request.getCategory() != null &&
            request.getCategory().equals(content.getCategory())){
            score += 2;
        }

        // Rule 4: tag match (OR logic)
        Set<String> requestTags= request.getTags();
        if(requestTags != null && content.getTags() != null){
            boolean tagMatch = content.getTags().stream()
                    .anyMatch(requestTags::contains);
            if(tagMatch){
                score += 3;
            }
        }

        // Rule 5: popularity bonus
        if(content.getViews() != null && content.getViews() > 100){
            score += 1;
        }

        return score;
    }

}
