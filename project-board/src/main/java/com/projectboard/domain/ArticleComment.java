package com.projectboard.domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Table(indexes = {
		@Index(columnList = "content"),
		@Index(columnList = "createdAt"),
		@Index(columnList = "createdBy")
})
@Entity
public class ArticleComment extends AuditingFields{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Setter
	@ManyToOne(optional = false)
	private Article article;
	
	@Setter
	@Column(nullable = false, length = 500)
	private String content;
	
	// @NoArgsConstructor 어노테이션이랑 동일
	protected ArticleComment() {
		
	}

	public ArticleComment(Article article, String content) {
		this.article = article;
		this.content = content;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleComment other = (ArticleComment) obj;
		return Objects.equals(id, other.id);
	}
	
}
