package com.crossover.techtrial.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	/**
	 * Search Articles Table matching the title and return result with
	 * pagination.
	 *
	 * @param title
	 *                A string that needs to be contained within article's
	 *                title. Case is ignored.
	 * @param pageRequest
	 *                a page request object containing all information
	 *                required to obtain the specific result page
	 * @return the requested page containing articles filtered by title
	 */
	Page<Article> search(String title, Pageable pageRequest);

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
