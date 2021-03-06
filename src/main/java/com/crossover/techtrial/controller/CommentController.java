package com.crossover.techtrial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.service.ArticleService;
import com.crossover.techtrial.service.CommentService;

@RestController
public class CommentController
{
	@Autowired
	ArticleService articleService;

	@Autowired
	CommentService commentService;

	@PostMapping(path = "articles/{article-id}/comments")
	public ResponseEntity<Comment> createComment(
		@PathVariable(value = "article-id") Long articleId,
		@RequestBody Comment comment)
	{
		return commentService.addComment(articleId, comment)
			.map(c -> new ResponseEntity<>(c, HttpStatus.CREATED))
			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping(path = "articles/{article-id}/comments")
	public ResponseEntity<List<Comment>> getComments(
		@PathVariable("article-id") Long articleId)
	{
		return new ResponseEntity<>(commentService.findAll(articleId),
			HttpStatus.OK);
	}
}
