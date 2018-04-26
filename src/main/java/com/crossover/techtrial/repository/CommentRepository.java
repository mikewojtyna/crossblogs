package com.crossover.techtrial.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.crossover.techtrial.model.Comment;

public interface CommentRepository
	extends PagingAndSortingRepository<Comment, Long>
{
	List<Comment> findByArticleId(long articleId);
}
