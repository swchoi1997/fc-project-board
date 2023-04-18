package com.example.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Article extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String hashtag;

    public Article() {
    }

    public Article(Long id, String title, String content, String hashtag) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }
}
