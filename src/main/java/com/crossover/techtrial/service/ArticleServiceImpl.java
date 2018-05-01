package com.crossover.techtrial.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService
{

	@Autowired
	ArticleRepository articleRepository;

	@Override
	public void delete(Long id)
	{
		articleRepository.deleteById(id);
	}

	@Override
	public Optional<Article> findById(Long id)
	{
		return articleRepository.findById(id);
	}

	@Override
	public Article save(Article article)
	{
		return articleRepository.save(article);
	}

	@Override
	public List<Article> search(String search)
	{
		return articleRepository
			.findByTitleContainingIgnoreCase(search);
	}

	/* (non-Javadoc)
	 * @see com.crossover.techtrial.service.ArticleService#update(java.lang.Long, com.crossover.techtrial.model.Article)
	 */
	@Override
	public boolean update(Long id, Article article)
	{
		Optional<Article> oldArticle = articleRepository.findById(id);
		oldArticle.ifPresent(old -> updateOldArticle(old, article));
		return oldArticle.map(old -> true).orElse(false);
	}

	/**
	 * @param oldArticle
	 * @param updatedArticle
	 */
	private void updateOldArticle(Article oldArticle,
		Article updatedArticle)
	{
		oldArticle.setContent(updatedArticle.getContent());
		oldArticle.setDate(updatedArticle.getDate());
		oldArticle.setEmail(updatedArticle.getEmail());
		oldArticle.setPublished(updatedArticle.getPublished());
		oldArticle.setTitle(updatedArticle.getTitle());
		articleRepository.save(oldArticle);
	}

}