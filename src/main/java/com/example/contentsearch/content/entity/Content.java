package com.example.contentsearch.content.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "contents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    private String category;

    @ElementCollection
    @CollectionTable(
            name = "content_tags",
            joinColumns = @JoinColumn(name = "content_id")
    )
    @Column(name = "tag")
    private Set<String> tags;

    @Column(nullable = false)
    private String status; // DRAFT, PUBLISHED

    private LocalDateTime publishedAt;

    @Column(nullable = false)
    private Long views;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        if (this.views == null) {
            this.views = 0L;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
