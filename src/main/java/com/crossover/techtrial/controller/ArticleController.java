package com.crossover.techtrial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.service.ArticleService;

@RestController
public class ArticleController
{

	@Autowired
	ArticleService articleService;

	@PostMapping(path = "articles")
	public ResponseEntity<Article> createArticle(
		@RequestBody Article article)
	{
		return new ResponseEntity<>(articleService.save(article),
			HttpStatus.CREATED);
	}

	@DeleteMapping(path = "articles/{article-id}")
	public ResponseEntity<Article> deleteArticleById(
		@PathVariable("article-id") Long id)
	{
		articleService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "articles/{article-id}")
	public ResponseEntity<Article> getArticleById(
		@PathVariable("article-id") Long id)
	{
		return articleService.findById(id).map(
			article -> new ResponseEntity(article, HttpStatus.OK))
			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/*
	 * Search endpoint is used in autocomplete and return only top 10 results. Therefore no need for
	 * pagination.
	 */

	/**
	 * Filters articles by title.
	 *
	 * @param text
	 *                a title text
	 * @param pageRequest
	 *                a page request object to determine the page result
	 * @return the response entity with 200 status and requested page body
	 */
	@GetMapping(path = "articles/search")
	public ResponseEntity<Page<Article>> searchArticles(
		@RequestParam(value = "text") String text, Pageable pageRequest)
	{
		return new ResponseEntity<>(
			articleService.search(text, pageRequest),
			HttpStatus.OK);
	}

	@PutMapping(path = "articles/{article-id}")
	public ResponseEntity<Void> updateArticle(
		@PathVariable("article-id") Long id,
		@RequestBody Article article)
	{
		boolean articleUpdated = articleService.update(id, article);
		if (articleUpdated)
		{
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
