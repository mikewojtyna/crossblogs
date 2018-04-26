/**
 *
 */
package com.crossover.techtrial.service;

import java.util.Random;
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

}
