package com.crossover.techtrial.service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.crossover.techtrial.model.Article;
/*
 * This interface provides all methods to access the functionality. See ArticleServiceImpl for implementation.
 * 
 * @author Khurram
 */
public interface ArticleService {
	/*
	 * Returns all the Articles along with Pagination information.
	 */
	List<Article> findAll();
	
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
