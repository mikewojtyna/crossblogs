package com.crossover.techtrial;
import com.crossover.techtrial.dto.ArticlesDTO;
import com.crossover.techtrial.dto.CommentsDTO;
import com.crossover.techtrial.model.*;
import java.util.Optional;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrossBlogController {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@PostMapping(path="articles")
	public ResponseEntity<Article> createArticle(@RequestBody Article article)
	{
		return new ResponseEntity<Article>(articleRepository.save(article),HttpStatus.CREATED);
		
	}
	
	@GetMapping(path="articles")
	public ResponseEntity<?> getArticles(@RequestParam(value="pageNumber")Long pageNumber,@RequestParam(value="pageSize")Long pageSize)
	{
		ArticlesDTO articlesDto = new ArticlesDTO();
		articlesDto.setArticles(articleRepository.findAll());
		return new ResponseEntity<ArticlesDTO>(articlesDto,HttpStatus.OK);
	}
	@GetMapping(path="articles/{article-id}")
	public ResponseEntity<Article> getArticleById(@PathVariable("article-id") Long id)
	{
		Optional<Article> article = articleRepository.findById(id);
		if (article.isPresent())
			return new  ResponseEntity<Article>(article.get(),HttpStatus.OK);
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path="articles/{article-id}")
	public ResponseEntity<Article> updateArticle(@PathVariable("article-id") Long id,@RequestBody Article article)
	{	
		Assert.assertNotEquals(id, article.getId());
		return new ResponseEntity<Article>(articleRepository.save(article),HttpStatus.OK);
	}
	@DeleteMapping(path="articles/{article-id}")
	public ResponseEntity<Article> deleteArticleById(@PathVariable("article-id") Long id)
	{
		articleRepository.deleteById(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping(path="articles/search")
	public ResponseEntity<?> searchArticles(@RequestParam(value="title")String title,@RequestParam(value="pageNumber")Long pageNumber,@RequestParam(value="pageSize")Long pageSize)
	{
		ArticlesDTO articlesDto = new ArticlesDTO();
		articlesDto.setArticles(articleRepository.findAll());
		return new ResponseEntity<ArticlesDTO>(articlesDto,HttpStatus.OK);
	}
	
	@PostMapping(path="articles/{article-id}/comments")
	public ResponseEntity<Comment> createComment(@PathVariable(value="article-id")Long articleId, @RequestBody Comment comment)
	{
		comment.setArticleId(articleId); 
		return new ResponseEntity<Comment>(commentRepository.save(comment),HttpStatus.CREATED);
	}
	@GetMapping(path="articles/{article-id}/comments")
	public ResponseEntity<CommentsDTO> getComments(@PathVariable("article-id") Long articleId, @RequestParam(value="pageNumber") Long pageNumber, @RequestParam(value="pageSize")Long pageSize)
	{
		CommentsDTO commentsDto = new CommentsDTO();
		commentsDto.setComments(commentRepository.findAll());
		return new ResponseEntity<CommentsDTO>(commentsDto,HttpStatus.CREATED);
		
	}
}
