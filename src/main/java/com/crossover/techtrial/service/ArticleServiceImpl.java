package com.crossover.techtrial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Override
	public Page<Article> findAll(Long pageNumber,Long pageSize){
		return articleRepository.findAll(PageRequest.of(pageNumber.intValue(), pageSize.intValue()));
	}
	
	@Override
	public Article save (Article article)
	{
		
		return articleRepository.save(article);
	}
	
	@Override
	public Article findById(Long id)
	{
		return articleRepository.findById(id).get();
	}
	
	@Override
	public void delete(Long id) {
		articleRepository.deleteById(id);
	}
	
	@Override
	public Page<Article> search(String title, Long pageNumber, Long pageSize){
		return articleRepository.findByTitleContainingIgnoreCase(title,PageRequest.of(pageNumber.intValue(), pageSize.intValue()));
	}
	
	
}