package com.example.contentsearch.content.repository;

import com.example.contentsearch.content.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query(
            value = """
            SELECT DISTINCT c
            FROM Content c
            LEFT JOIN FETCH c.tags
            WHERE c.status = 'PUBLISHED'
              AND (
                    LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                 OR LOWER(c.body)  LIKE LOWER(CONCAT('%', :keyword, '%'))
              )
            """,
            countQuery = """
            SELECT COUNT(c)
            FROM Content c
            WHERE c.status = 'PUBLISHED'
              AND (
                    LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                 OR LOWER(c.body)  LIKE LOWER(CONCAT('%', :keyword, '%'))
              )
            """
    )
    Page<Content> searchByKeyword(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
