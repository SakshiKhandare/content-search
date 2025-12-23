package com.example.contentsearch.content.repository;

import com.example.contentsearch.content.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query(
            value = """
            SELECT DISTINCT c
            FROM Content c
            LEFT JOIN FETCH c.tags t
            WHERE c.status = 'PUBLISHED'
              AND (
                    LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                 OR LOWER(c.body)  LIKE LOWER(CONCAT('%', :keyword, '%'))
              )
              AND (:category IS NULL OR c.category = :category)
              AND (
                    :tags IS NULL
                 OR t IN :tags
              )
            """,
            countQuery = """
            SELECT COUNT(DISTINCT c)
            FROM Content c
            LEFT JOIN c.tags t
            WHERE c.status = 'PUBLISHED'
              AND (
                    LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                 OR LOWER(c.body)  LIKE LOWER(CONCAT('%', :keyword, '%'))
              )
              AND (:category IS NULL OR c.category = :category)
              AND (
                    :tags IS NULL
                 OR t IN :tags
              )
            """
    )
    Page<Content> search(
            @Param("keyword") String keyword,
            @Param("category") String category,
            @Param("tags") Set<String> tags,
            Pageable pageable
    );
}
