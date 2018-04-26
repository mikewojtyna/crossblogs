package com.crossover.techtrial.service;

import java.util.List;
import java.util.Optional;
import com.crossover.techtrial.model.Article;

/*
 * This interface provides all methods to access the functionality. See ArticleServiceImpl for implementation.
 *
 * @author crossover
 */
public interface ArticleService
{
	/*
	 * Delete a particular article with id
	 */
	void delete(Long id);

	/*
	 * FindById will find the specific user form list.
	 *
	 */
	Optional<Article> findById(Long id);

	/*
	 * Save the default article.
	 */
	Article save(Article article);

	/*
	 * Search Articles Table matching the title and return result with pagination.
	 */
	List<Article> search(String title);

	/**
	 * Updates existing article entity.
	 *
	 * @param id
	 *                article id
	 * @param article
	 *                new article (all properties except id will be copied)
	 * @return {@code true} if article was updated successfully,
	 *         {@code false} when article doesn't exist
	 */
	boolean update(Long id, Article article);

}
