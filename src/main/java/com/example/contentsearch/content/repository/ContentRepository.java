package com.example.contentsearch.content.repository;

import com.example.contentsearch.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
