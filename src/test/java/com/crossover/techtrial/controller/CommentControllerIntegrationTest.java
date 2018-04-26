/**
 *
 */
package com.crossover.techtrial.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.service.CommentFixtureUtils;
import com.crossover.techtrial.service.CommentService;

/**
 * @author goobar
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SuppressWarnings("javadoc")
public class CommentControllerIntegrationTest
{

	@MockBean
	private CommentService commentService;

	@Autowired
	private TestRestTemplate template;

	@Test
	public void should_CreateComment() throws Exception
	{
		// given
		HttpEntity<Object> request = ControllerTestUtils
			.getHttpEntity("{ \"email\": \"test@email.com\" }");
		Comment requestComment = commentWithEmail("test@email.com");
		long articleId = anyArticleId();
		Comment expectedCreatedComment = randomComment();
		// configure comment service to add comment
		when(commentService.addComment(articleId, requestComment))
			.thenReturn(Optional.of(expectedCreatedComment));

		// when
		ResponseEntity<Comment> responseEntity = template.postForEntity(
			"/articles/{id}/comments", request, Comment.class,
			Collections.singletonMap("id", articleId));

		// then
		assertThat(responseEntity.getStatusCode())
			.isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody())
			.isEqualTo(expectedCreatedComment);
	}

	@Test
	public void should_GetComments() throws Exception
	{
		// given
		long articleId = anyArticleId();
		// configure comment service to return comments
		Comment expectedComment0 = randomComment();
		Comment expectedComment1 = randomComment();
		when(commentService.findAll(articleId)).thenReturn(
			Arrays.asList(expectedComment0, expectedComment1));

		// when
		ResponseEntity<List<Comment>> responseEntity = template
			.exchange("/articles/{id}/comments", HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Comment>>()
				{
				}, Collections.singletonMap("id", articleId));

		// then
		assertThat(responseEntity.getStatusCode())
			.isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody())
			.containsOnly(expectedComment0, expectedComment1);
	}

	@Test
	public void should_NotCreateComment_When_ArticleDoesntExist()
		throws Exception
	{
		// given
		HttpEntity<Object> request = ControllerTestUtils
			.getHttpEntity("{ \"email\": \"test@email.com\" }");
		Comment requestComment = commentWithEmail("test@email.com");
		long articleId = anyArticleId();
		// configure comment service so article doesn't exist
		when(commentService.addComment(articleId, requestComment))
			.thenReturn(Optional.empty());

		// when
		ResponseEntity<Comment> responseEntity = template.postForEntity(
			"/articles/{id}/comments", request, Comment.class,
			Collections.singletonMap("id", articleId));

		// then
		assertThat(responseEntity.getStatusCode())
			.isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(responseEntity.getBody()).isNull();
	}

	/**
	 * @return
	 */
	private long anyArticleId()
	{
		return 1L;
	}

	/**
	 * @param email
	 * @return
	 */
	private Comment commentWithEmail(String email)
	{
		return CommentFixtureUtils.withEmail(email);
	}

	/**
	 * @return
	 */
	private Comment randomComment()
	{
		return CommentFixtureUtils.randomComment();
	}

}
