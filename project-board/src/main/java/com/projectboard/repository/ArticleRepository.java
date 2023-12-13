package com.projectboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.projectboard.domain.Article;
import com.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.StringExpression;

@RepositoryRestResource
public interface ArticleRepository
		extends JpaRepository<Article, Long>, QuerydslPredicateExecutor<Article>, QuerydslBinderCustomizer<QArticle> {
	
	@Override
	default void customize(QuerydslBindings bindings, QArticle root) {
		bindings.excludeUnlistedProperties(true);
		bindings.including(root.title, root.content, root.hashTag, root.createdAt, root.createdBy);
		
//		bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like '${v}'
		bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%${v}%'
		bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
		bindings.bind(root.hashTag).first(StringExpression::containsIgnoreCase);
		bindings.bind(root.createdAt).first((path, value) -> path.eq(value));
		bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);

	}
}
