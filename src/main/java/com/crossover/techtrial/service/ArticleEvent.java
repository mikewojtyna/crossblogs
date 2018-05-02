/**
 *
 */
package com.crossover.techtrial.service;

/**
 * An event published when operation on article is performed (e.g. new article
 * is created). This allows us to easily scale articles endpoint.
 *
 * @author goobar
 *
 */
public class ArticleEvent
{
	private final long articleId;

	private final ArticleOperation operation;

	/**
	 * @param articleId
	 *                an article id
	 * @param operation
	 *                an operation on the article
	 */
	public ArticleEvent(Long articleId, ArticleOperation operation)
	{
		this.articleId = articleId;
		this.operation = operation;
	}

	/**
	 * @author goobar
	 *
	 */
	public enum ArticleOperation
	{
		CREATED, DELETED, UPDATED
	}

}
