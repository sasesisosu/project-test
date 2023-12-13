package com.projectboard.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.projectboard.config.JpaConfig;
import com.projectboard.domain.Article;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
public class JpaRepositoryTest {

	private final ArticleRepository articleRepository;
	private final ArticleCommentRepository articleCommentRepository;

	public JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
							 @Autowired ArticleCommentRepository articleCommentRepository) {
		this.articleRepository = articleRepository;
		this.articleCommentRepository = articleCommentRepository;
	}

	
	@DisplayName("select 테스트")
	@Test
	void givenTestData_whenSelecting_thenWorksFine() {
		// Given
		
		// When
		List<Article> articles = articleRepository.findAll();
		
		// Then
		assertThat(articles)
				.isNotNull()
				.hasSize(0);
	}
	
	@DisplayName("insert 테스트")
	@Test
	void givenTestData_whenInserting_thenWorksFine() {
		// Given
		long previousCount = articleRepository.count();
		
		// When
		Article savedArticle = articleRepository.save(new Article("new article", "new content", "#spring"));
		
		// Then
		assertThat(articleRepository.count())
				.isEqualTo(previousCount + 1);
	}
	
	@DisplayName("update 테스트")
	@Test
	void givenTestData_whenUpdating_thenWorksFine() {
		// Given
		Article savedArticle = articleRepository.saveAndFlush(new Article("new article", "new content", "#spring"));
		System.out.println("testtest : " + articleRepository.findAll());
		Article article = articleRepository.findById(3L).orElseThrow();
		String updatedHashtag = "#springboot";
		article.setHashTag(updatedHashtag);
		
		// When
		Article savedArticle2 = articleRepository.saveAndFlush(article);
		
		// Then
		assertThat(savedArticle2).hasFieldOrPropertyWithValue("hashTag", updatedHashtag);
	}
	
	@DisplayName("delete 테스트")
	@Test
	void givenTestData_whenDeleting_thenWorksFine() {
		// Given
		Article savedArticle = articleRepository.saveAndFlush(new Article("new article", "new content", "#spring"));
//		ArticleComment savedArticleComment = articleCommentRepository.saveAndFlush(new ArticleComment(savedArticle, "new comment"));
		Article article = articleRepository.findById(2L).orElseThrow();
		long previousArticleCount =  articleRepository.count();
		long previousArticleCommentCount =  articleCommentRepository.count();
		int deletedCommentsSize = article.getArticleComments().size();
	
		// When
		articleRepository.delete(article);
		
		// Then
		assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
//		assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - 1);
	}
}





