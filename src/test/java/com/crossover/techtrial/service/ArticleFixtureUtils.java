/**
 *
 */
package com.crossover.techtrial.service;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import com.crossover.techtrial.model.Article;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ArticleFixtureUtils
{
	public static Article anyArticle()
	{
		Article article = new Article();
		article.setEmail("test@email.com");
		return article;
	}

	/**
	 * @param email
	 * @return
	 */
	public static Article articleWithEmail(String email)
	{
		Article article = anyArticle();
		article.setEmail(email);
		return article;
	}

	/**
	 * @param title
	 * @return
	 */
	public static Article articleWithTitle(String title)
	{
		Article article = anyArticle();
		article.setTitle(title);
		return article;
	}

	/**
	 * Creates builder to build {@link Article}.
	 *
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link Article}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private String content;

		private LocalDateTime date;

		private String email;

		private Long id;

		private Boolean published;

		private String title;

		private Builder()
		{
		}

		public Article build()
		{
			Article article = new Article();
			article.setContent(content);
			article.setDate(date);
			article.setEmail(email);
			article.setId(id);
			article.setPublished(published);
			article.setTitle(title);
			return article;
		}

		public Builder withContent(String content)
		{
			this.content = content;
			return this;
		}

		public Builder withDate(LocalDateTime date)
		{
			this.date = date;
			return this;
		}

		public Builder withEmail(String email)
		{
			this.email = email;
			return this;
		}

		public Builder withId(Long id)
		{
			this.id = id;
			return this;
		}

		public Builder withPublished(Boolean published)
		{
			this.published = published;
			return this;
		}

		public Builder withTitle(String title)
		{
			this.title = title;
			return this;
		}
	}
}
