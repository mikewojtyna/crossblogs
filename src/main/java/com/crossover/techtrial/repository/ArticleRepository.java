package com.crossover.techtrial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.crossover.techtrial.model.Article;

@RepositoryRestResource(exported=false)
public interface ArticleRepository extends PagingAndSortingRepository<Article,Long> {
	
	@Query("select a from Article a where a.title like %:search% or a.content like %:search%")
	List<Article> findByTitleOrContent(@Param("search")String search);
	List<Article> findAll();
	
}
