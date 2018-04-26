/**
 *
 */
package com.crossover.techtrial.service;

import java.time.LocalDateTime;
import java.util.Random;
import javax.annotation.Generated;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.model.Comment;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class CommentFixtureUtils
{

	/**
	 * @return
	 */
	public static Comment anyComment()
	{
		Comment comment = new Comment();
		comment.setEmail("test@email.com");
		return comment;
	}

	/**
	 * Creates builder to build {@link Comment}.
	 *
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * @return
	 */
	public static Comment randomComment()
	{
		Comment comment = anyComment();
		comment.setId(randomId());
		return comment;
	}

	/**
	 * @param article
	 * @return
	 */
	public static Comment withArticle(Article article)
	{
		Comment comment = anyComment();
		comment.setArticle(article);
		return comment;
	}

	/**
	 * @param email
	 * @return
	 */
	public static Comment withEmail(String email)
	{
		Comment comment = anyComment();
		comment.setEmail(email);
		return comment;
	}

	/**
	 * @param message
	 * @return
	 */
	public static Comment withMessage(String message)
	{
		Comment comment = anyComment();
		comment.setMessage(message);
		return comment;
	}

	/**
	 * @return
	 */
	private static Long randomId()
	{
		return new Random().nextLong();
	}

	/**
	 * Builder to build {@link Comment}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private Article article;

		private LocalDateTime date;

		private String email;

		private Long id;

		private String message;

		private Builder()
		{
		}

		public Comment build()
		{
			Comment comment = new Comment();
			comment.setArticle(article);
			comment.setDate(date);
			comment.setEmail(email);
			comment.setId(id);
			comment.setMessage(message);
			return comment;
		}

		public Builder withArticle(Article article)
		{
			this.article = article;
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

		public Builder withMessage(String message)
		{
			this.message = message;
			return this;
		}
	}

}
