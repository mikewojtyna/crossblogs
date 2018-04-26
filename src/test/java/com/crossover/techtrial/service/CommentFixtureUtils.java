/**
 *
 */
package com.crossover.techtrial.service;

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
	 * @param message
	 * @return
	 */
	public static Comment withMessage(String message)
	{
		Comment comment = anyComment();
		comment.setMessage(message);
		return comment;
	}

}
