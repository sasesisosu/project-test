package com.projectboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectboard.domain.ArticleComment;
import com.projectboard.domain.QArticleComment;
import com.querydsl.core.types.dsl.StringExpression;

@RepositoryRestResource
public interface ArticleCommentRepository
		extends JpaRepository<ArticleComment, Long>, QuerydslPredicateExecutor<ArticleComment>, QuerydslBinderCustomizer<QArticleComment> {

	@Override
	default void customize(QuerydslBindings bindings, QArticleComment root) {
		bindings.excludeUnlistedProperties(true);
		bindings.including(root.content, root.createdAt, root.createdBy);

		bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
		bindings.bind(root.createdAt).first((path, value) -> path.eq(value));
		bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
	}
	
}
