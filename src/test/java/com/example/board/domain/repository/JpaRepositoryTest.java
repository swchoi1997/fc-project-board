package com.example.board.domain.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.board.domain.Article;
import com.example.board.global.config.JpaConfig;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@DisplayName("Jpa Connection Test")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private ArticleRepository articleRepository;
    private ArticleCommentRepository articleCommentRepository;

    @Autowired
    public JpaRepositoryTest(ArticleRepository articleRepository, ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("Select Test")
    @Test
    public void whenSelectiong_ThenWorksFinal() throws Exception {
        //given //when
        List<Article> all = articleRepository.findAll();

        //then
        assertThat(all).isNotNull().hasSize(0);
    }

    @DisplayName("InsertTest")
    @Test
    public void whenInserting() throws Exception{
        //given
        long count = articleRepository.count();
        //when
        articleRepository.save(new Article("test1", "test1", "test1"));
        //then
        assertThat(count + 1).isEqualTo(articleRepository.count());
    }
}
