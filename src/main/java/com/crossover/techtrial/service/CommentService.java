package com.crossover.techtrial.service;

import java.util.List;
import java.util.Optional;
import com.crossover.techtrial.model.Comment;

public interface CommentService
{

	/**
	 * Adds a comment to the article.
	 *
	 * @param articleId
	 *                id of the article
	 * @param comment
	 *                comment to add
	 * @return a created comment or empty result if article doesn't exist
	 */
	Optional<Comment> addComment(long articleId, Comment comment);

	/*
	 * Returns all the Comments related to article along with Pagination information.
	 */
	List<Comment> findAll(Long articleId);

	/*
	 * Save the default article.
	 */
	Comment save(Comment comment);

}
