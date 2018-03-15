package com.crossover.techtrial.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.crossover.techtrial.model.Comment;

@RepositoryRestResource(exported=false)
public interface CommentRepository extends PagingAndSortingRepository<Comment,Long> {
	
	@Override
	List<Comment> findAll();
	
	Page<Comment> findByArticleIdOrderByCreatedAt(Long articleId, Pageable pageable);
}
