package com.crossover.techtrial.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.repository.ArticleRepository;
import com.crossover.techtrial.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService
{

	private final ArticleRepository articleRepository;

	CommentRepository commentRepository;

	public CommentServiceImpl(CommentRepository commentRepository,
		ArticleRepository articleRepository)
	{
		this.commentRepository = commentRepository;
		this.articleRepository = articleRepository;
	}

	/* (non-Javadoc)
	 * @see com.crossover.techtrial.service.CommentService#addComment(long, com.crossover.techtrial.model.Comment)
	 */
	@Override
	public Optional<Comment> addComment(long articleId, Comment comment)
	{
		Optional<Article> article = articleRepository
			.findById(articleId);

		article.ifPresent(a -> comment.setArticle(a));
		return article.map(a -> Optional.of(save(comment)))
			.orElse(Optional.empty());
	}

	/*
	 * Returns all the Comments related to article along with Pagination information.
	 */
	@Override
	public List<Comment> findAll(Long articleId)
	{
		return commentRepository.findByArticleId(articleId);
	}

	/*
	 * Save the default article.
	 */
	@Override
	public Comment save(Comment comment)
	{
		return commentRepository.save(comment);
	}

}
