/**
 *
 */
package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Article;

/**
 * @author goobar
 *
 */
public class ArticleTestFixtureUtils
{
	public static Article anyArticle()
	{
		Article article = new Article();
		article.setEmail("test@email.com");
		return article;
	}
}
