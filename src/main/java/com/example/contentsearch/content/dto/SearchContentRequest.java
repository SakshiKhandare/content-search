package com.example.contentsearch.content.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
