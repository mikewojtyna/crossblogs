package com.crossover.techtrial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.crossover.techtrial.model.Article;

@RepositoryRestResource(exported=false)
public interface ArticleRepository extends PagingAndSortingRepository<Article,Long> {	
	
		List<Article> findTop10ByTitleOrContent(@Param("search")String search);
}
