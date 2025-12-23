package com.example.contentsearch.content.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchContentRequest {

    @NotBlank
    private String keyword;

    @Min(0)
    private int page = 0;

    @Min(1)
    private int size = 10;

    private String sortBy = "publishedAt";

    private String sortDirection = "DESC";

    /**
     * Optional exact-match category filter
     */
    private String category;

    /**
     * Optional OR-based tag filter.
     * Example: tags=java,spring
     */
    private Set<String> tags;
}
