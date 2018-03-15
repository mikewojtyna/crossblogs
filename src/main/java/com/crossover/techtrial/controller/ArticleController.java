package com.crossover.techtrial.controller;

import org.junit.Assert;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.crossover.techtrial.dto.ArticlesDTO;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.service.ArticleService;

import util.MappingUtil;

@RestController
public class ArticleController {
	
	@Autowired
	ArticleService articleService; 
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping(path="articles")
	public ResponseEntity<Article> createArticle(@RequestBody Article article)
	{
		return new ResponseEntity<Article>(articleService.save(article),HttpStatus.CREATED);
		
	}
	
	@GetMapping(path="articles")
	public ResponseEntity<?> getArticles(@RequestParam(value="pageNumber")Long pageNumber,@RequestParam(value="pageSize")Long pageSize)
	{
		Page<Article> page =articleService.findAll(pageNumber, pageSize);
		return new ResponseEntity<ArticlesDTO>(MappingUtil.mapToArticlesDto(page),HttpStatus.OK);
	}
	@GetMapping(path="articles/{article-id}")
	public ResponseEntity<Article> getArticleById(@PathVariable("article-id") Long id)
	{
		Article article = articleService.findById(id);
		if (article!=null)
			return new  ResponseEntity<Article>(article,HttpStatus.OK);
		return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path="articles/{article-id}")
	public ResponseEntity<Article> updateArticle(@PathVariable("article-id") Long id,@RequestBody Article article)
	{	
		Assert.assertNotEquals(id, article.getId());
		return new ResponseEntity<Article>(articleService.save(article),HttpStatus.OK);
	}
	@DeleteMapping(path="articles/{article-id}")
	public ResponseEntity<Article> deleteArticleById(@PathVariable("article-id") Long id)
	{
		articleService.delete(id);;
		return new ResponseEntity<Article>(HttpStatus.OK);
	}

	@GetMapping(path="articles/search")
	public ResponseEntity<?> searchArticles(@RequestParam(value="title")String title,@RequestParam(value="pageNumber")Long pageNumber,@RequestParam(value="pageSize")Long pageSize)
	{
		ArticlesDTO articlesDto = MappingUtil.mapToArticlesDto(articleService.search(title, pageNumber, pageSize));
		return new ResponseEntity<ArticlesDTO>(articlesDto,HttpStatus.OK);
	}

}
