package com.crossover.techtrial.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.crossover.techtrial.model.Article;

public class ArticlesDTO {
	@Getter @Setter Integer pageNumber;
	@Getter @Setter Integer pageSize;
	@Getter @Setter Integer pageCount;
	@Getter @Setter List<Article> articles;
}
