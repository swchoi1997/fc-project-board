package com.example.board.domain.repository;

import com.example.board.domain.ArticleComment;
import com.example.board.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root){
        bindings.excludeUnlistedProperties(true); //선택적으로 검색 가능하게 함
        bindings.including(root.content, root.createdAt, root.createdBy); // 검색 원하는 필드 추가

        //아래 두개의 차이는 쿼리 생성되는 문장이 다르다
//        bindings.bind(root.content).first((StringExpression::likeIgnoreCase)); // Like ''
        bindings.bind(root.content).first((StringExpression::containsIgnoreCase)); //like '%$(v)%' -> Index를 타지 못함?
        bindings.bind(root.createdAt).first((DateTimeExpression::eq));
        bindings.bind(root.createdBy).first((StringExpression::containsIgnoreCase));

    }
}
