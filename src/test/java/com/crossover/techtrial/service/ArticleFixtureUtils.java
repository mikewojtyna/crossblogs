/**
 *
 */
package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Article;

/**
 * @author goobar
 *
 */
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
}
