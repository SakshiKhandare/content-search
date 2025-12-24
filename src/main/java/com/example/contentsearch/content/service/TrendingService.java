package com.example.contentsearch.content.service;

import com.example.contentsearch.content.entity.Content;
import com.example.contentsearch.content.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TrendingService {

    private static final int DEFAULT_DAYS = 7;
    private static final int MAX_DAYS = 30;

    private final ContentRepository contentRepository;

    public Page<Content> getTrending(int days, int page, int size) {

        int effectiveDays = normalizeDays(days);
        validatePagination(page, size);

        LocalDateTime since = LocalDateTime.now().minusDays(effectiveDays);

        Sort sort = Sort.by(
                Sort.Order.desc("views"),
                Sort.Order.desc("publishedAt")
        );

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return contentRepository.findTrending(since, pageRequest);
    }

    private int normalizeDays(int days) {
        if (days <= 0) {
            return DEFAULT_DAYS;
        }
        if (days > MAX_DAYS) {
            throw new IllegalArgumentException("days must be between 1 and " + MAX_DAYS);
        }
        return days;
    }

    private void validatePagination(int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException("page must be >= 0");
        }
        if (size <= 0 || size > 50) {
            throw new IllegalArgumentException("size must be between 1 and 50");
        }
    }
}
