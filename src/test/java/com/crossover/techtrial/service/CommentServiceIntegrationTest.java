/**
 *
 */
package com.crossover.techtrial.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
	public void should_AddComment_To_ExistingArticle() throws Exception
	{
		// given
		// add some existing articles
		Article targetArticle = createNewArticle();
		createNewArticle();
		createNewArticle();
		// comment to add
		Comment comment = CommentFixtureUtils
			.withMessage("hello, this is a message!");

		// when
		Comment newComment = service
			.addComment(targetArticle.getId(), comment).get();

		// then
		assertThat(newComment.getMessage())
			.isEqualTo(comment.getMessage());
		assertThat(findComments(targetArticle.getId()))
			.containsOnly(newComment);
	}

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
		// create requested article with some comments
		Article requestedArticle = createNewArticle();
		Long requestedArticleId = requestedArticle.getId();
		// there are 3 comments for first article
		Comment comment0 = createCommentForArticle(requestedArticle);
		Comment comment1 = createCommentForArticle(requestedArticle);
		Comment comment2 = createCommentForArticle(requestedArticle);
		// lets add another irrelevant article and comments
		Article otherArticle = createNewArticle();
		createCommentForArticle(otherArticle);
		createCommentForArticle(otherArticle);

		// when
		List<Comment> commentsOfFirstArticle = service
			.findAll(requestedArticleId);

		// then
		assertThat(commentsOfFirstArticle).containsOnly(comment0,
			comment1, comment2);
	}

	@Test
	public void should_NotAddComment_To_NonExistentArticle()
		throws Exception
	{
		// given
		// add some articles
		createNewArticle();
		createNewArticle();
		Comment comment = anyComment();
		long nonExistendArticleId = nonExistentArticleId();

		// when
		Optional<Comment> addedComment = service
			.addComment(nonExistendArticleId, comment);

		// then
		assertThat(addedComment).isNotPresent();
		assertThat(findComments(nonExistendArticleId)).isEmpty();
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
		return CommentFixtureUtils.anyComment();
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
	 * @param article
	 * @return
	 */
	private Comment createCommentForArticle(Article article)
	{
		return service.save(CommentFixtureUtils.withArticle(article));
	}

	/**
	 * @param articleId
	 * @return
	 */
	private Article createNewArticle()
	{
		return articleService.save(ArticleFixtureUtils.anyArticle());
	}

	/**
	 * @param articleId
	 * @return
	 */
	private Collection<Comment> findComments(Long articleId)
	{
		return service.findAll(articleId);
	}

	/**
	 * @return
	 */
	private long nonExistentArticleId()
	{
		return -777L;
	}

}
