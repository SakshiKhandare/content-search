package com.example.contentsearch.content.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateContentRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    @NotBlank
    private String category;

    @NotEmpty
    private Set<String> tags;
}
