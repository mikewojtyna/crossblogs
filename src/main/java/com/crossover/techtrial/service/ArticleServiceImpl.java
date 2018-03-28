package com.crossover.techtrial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Override
	public List<Article> findAll(){
		return articleRepository.findAll();
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
	public List<Article> search(String title){
		return articleRepository.findByTitleContainingIgnoreCase(title);
	}
	
	
}