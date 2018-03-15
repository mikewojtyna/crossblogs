package com.crossover.techtrial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.repository.CommentRepository;

@Service
public class CommentServiceImpl {

	@Autowired
	CommentRepository commentRepository;
	
	/*
	 * Returns all the Comments related to article along with Pagination information.
	 */
	Page<Comment> findAll(Long articleId, Long pageNumber,Long pageSize){
		return commentRepository.findByArticleIdOrderByCreatedAt(articleId,PageRequest.of(pageNumber.intValue(), pageSize.intValue()));
	}
	
	
	/*
	 * Save the default article.
	 */
	public Comment save(Comment comment)
	{
		return commentRepository.save(comment);
	}


}
