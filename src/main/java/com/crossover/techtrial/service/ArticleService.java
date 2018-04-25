package com.crossover.techtrial.service;

import java.util.List;
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
	Article findById(Long id);

	/*
	 * Save the default article.
	 */
	Article save(Article article);

	/*
	 * Search Articles Table matching the title and return result with pagination.
	 */
	List<Article> search(String title);

}
