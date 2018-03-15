package com.crossover.techtrial.service;

import org.springframework.data.domain.Page;
import com.crossover.techtrial.model.Comment;

public interface CommentService {
	

	/*
	 * Returns all the Comments related to article along with Pagination information.
	 */
	Page<Comment> findAll(Long articleId, Long pageNumber,Long pageSize);
	
	/*
	 * Save the default article.
	 */
	default Comment save(Comment comment)
	{
		return null;
	}

}
