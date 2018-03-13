package com.crossover.techtrial;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.crossover.techtrial.model.Article;

@RepositoryRestResource(exported=false)
public interface ArticleRepository extends CrudRepository<Article,Long> {
	
	@Override
	List<Article> findAll();
	
}
