package com.crossover.techtrial.service;

import java.util.List;

import com.crossover.techtrial.model.Article;
/*
 * This interface provides all methods to access the functionality. See ArticleServiceImpl for implementation.
 * 
 * @author Khurram
 */
public interface ArticleService {
	/*
	 * Save the default article.
	 */
	default Article save(Article article)
	{
		return null;
	}
	
	/*
	 * FindById will find the specific user form list.
	 * 
	 */
	Article findById(Long id);
	
	/*
	 * Delete a particular article with id
	 */
	void delete(Long id);
	
	/*
	 * Search Articles Table matching the title and return result with pagination.
	 */
	List<Article> search(String title);
	
}
