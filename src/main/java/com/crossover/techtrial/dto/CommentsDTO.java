package com.crossover.techtrial.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.model.Comment;

public class CommentsDTO {
	@Getter @Setter Integer pageNumber;
	@Getter @Setter Integer pageSize;
	@Getter @Setter Integer pageCount;
	@Getter @Setter List<Comment> comments;
}
