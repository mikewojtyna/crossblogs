/**
 *
 */
package com.crossover.techtrial.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.repository.ArticleRepository;
import com.crossover.techtrial.repository.CommentRepository;

/**
 * @author goobar
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("javadoc")
public class CommentServiceIntegrationTest
{
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CommentService service;

	@Test
	public void should_CreateComment_RelatedToArticle() throws Exception
	{
		// given
		Article article = createNewArticle();

		// when
		Comment savedComment = service.save(commentOfArticle(article));

		// then
		assertThat(savedComment.getArticle()).isEqualTo(article);
	}

	@Test
	public void should_CreateCommentWithoutArticle() throws Exception
	{
		// given
		Comment comment = anyComment();

		// when
		Comment savedComment = service.save(comment);

		// then
		assertThat(savedComment).isNotNull();
	}

	@Test
	public void should_FindAllComments_Of_Article() throws Exception
	{
		// given
		// create first article with some comments
		Article firstArticle = createNewArticle();
		Long firstArticleId = firstArticle.getId();
		// there are 3 comments for first article
		Comment comment0 = createCommentForArticle(firstArticleId);
		Comment comment1 = createCommentForArticle(firstArticleId);
		Comment comment2 = createCommentForArticle(firstArticleId);
		// lets add another irrelevant article
		createNewArticle();

		// when
		List<Comment> commentsOfFirstArticle = service
			.findAll(firstArticleId);

		// then
		assertThat(commentsOfFirstArticle).containsOnly(comment0,
			comment1, comment2);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		cleanDb();
	}

	/**
	 * @return
	 */
	private Comment anyComment()
	{
		Comment comment = new Comment();
		comment.setEmail("test@email.com");
		return comment;
	}

	/**
	 *
	 */
	private void cleanDb()
	{
		commentRepository.deleteAll();
		articleRepository.deleteAll();
	}

	/**
	 * @param article
	 * @return
	 */
	private Comment commentOfArticle(Article article)
	{
		Comment comment = anyComment();
		comment.setArticle(article);
		return comment;
	}

	/**
	 * @param articleId
	 * @return
	 */
	private Comment createCommentForArticle(Long articleId)
	{
		return service.save(anyComment());
	}

	/**
	 * @param articleId
	 * @return
	 */
	private Article createNewArticle()
	{
		return articleService.save(ArticleFixtureUtils.anyArticle());
	}

}
