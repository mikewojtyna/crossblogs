package com.crossover.techtrial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.dto.CommentsDTO;
import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.service.CommentService;

import util.MappingUtil;

@RestController
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@PostMapping(path="articles/{article-id}/comments")
	public ResponseEntity<Comment> createComment(@PathVariable(value="article-id")Long articleId, @RequestBody Comment comment)
	{
		comment.setArticleId(articleId); 
		return new ResponseEntity<Comment>(commentService.save(comment),HttpStatus.CREATED);
	}
	@GetMapping(path="articles/{article-id}/comments")
	public ResponseEntity<CommentsDTO> getComments(@PathVariable("article-id") Long articleId, @RequestParam(value="pageNumber") Long pageNumber, @RequestParam(value="pageSize")Long pageSize)
	{
		CommentsDTO commentsDto = MappingUtil.maptoCommentsDto(commentService.findAll(articleId,pageNumber,pageSize));
		return new ResponseEntity<CommentsDTO>(commentsDto,HttpStatus.OK);
		
	}
}
