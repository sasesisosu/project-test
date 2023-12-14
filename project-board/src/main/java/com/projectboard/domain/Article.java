package com.projectboard.domain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Table(indexes = {
		@Index(columnList = "title"),
		@Index(columnList = "hashTag"),
		@Index(columnList = "createdAt"),
		@Index(columnList = "createdBy")
})
@Entity
public class Article extends AuditingFields{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Setter
	@Column(nullable = false)
	private String title;
	
	@Setter
	@Column(nullable = false, length = 10000)
	private String content;
	
	@Setter
	private String hashTag;
	
	@ToString.Exclude
	@OrderBy("id")
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
//	@OneToMany(mappedBy = "article") - 실무에서는 보통 cascade 제거하고 함
	private final Set<ArticleComment> articleComments = new LinkedHashSet<>();
	

	protected Article() {
		
	}

	public Article(String title, String content, String hashTag) {
		this.title = title;
		this.content = content;
		this.hashTag = hashTag;
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
		Article other = (Article) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
