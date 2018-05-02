package com.crossover.techtrial.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.repository.ArticleRepository;
import com.crossover.techtrial.service.ArticleEvent.ArticleOperation;

@Service
public class ArticleServiceImpl implements ArticleService
{

	private final ArticleRepository articleRepository;

	private final EventPublisher<ArticleEvent> eventPublisher;

	public ArticleServiceImpl(ArticleRepository articleRepository,
		EventPublisher<ArticleEvent> eventPublisher)
	{
		this.articleRepository = articleRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void delete(Long id)
	{
		articleRepository.deleteById(id);
		// publish an event, so it can be later consumed by other nodes,
		// e.g. using separate NoSql read model
		eventPublisher.publishEvent(
			new ArticleEvent(id, ArticleOperation.DELETED));
	}

	@Override
	public Optional<Article> findById(Long id)
	{
		return articleRepository.findById(id);
	}

	@Override
	public Article save(Article article)
	{
		Article newArticle = articleRepository.save(article);
		// publish an event, so it can be later consumed by other nodes,
		// e.g. using separate NoSql read model
		eventPublisher.publishEvent(new ArticleEvent(newArticle.getId(),
			ArticleOperation.CREATED));
		return newArticle;
	}

	@Override
	public Page<Article> search(String search, Pageable pageRequest)
	{
		return articleRepository.findByTitleContainingIgnoreCase(search,
			pageRequest);
	}

	/* (non-Javadoc)
	 * @see com.crossover.techtrial.service.ArticleService#update(java.lang.Long, com.crossover.techtrial.model.Article)
	 */
	@Override
	public boolean update(Long id, Article article)
	{
		Optional<Article> oldArticle = articleRepository.findById(id);
		oldArticle.ifPresent(old -> updateOldArticle(old, article));
		// publish an event, so it can be later consumed by other nodes,
		// e.g. using separate NoSql read model
		eventPublisher.publishEvent(
			new ArticleEvent(id, ArticleOperation.UPDATED));
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