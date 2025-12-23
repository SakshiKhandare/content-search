package com.example.contentsearch.content.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CreateContentResponse {

    private final Long id;
    private final String status;
    private final LocalDateTime publishedAt;
}