package com.example.contentsearch.content.service;

import com.example.contentsearch.common.exception.ResourceNotFoundException;
import com.example.contentsearch.content.entity.Content;
import com.example.contentsearch.content.repository.ContentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentReadService {

    private final ContentRepository contentRepository;

    @Transactional
    public Content readAndIncrementViews(Long id) {
        Content content = contentRepository.findByIdWithTags(id)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found"));

        Long currentViews = content.getViews() == null ? 0L : content.getViews();
        content.setViews(currentViews + 1);

        // No explicit save needed; JPA dirty checking will persist the update
        return content;
    }
}
