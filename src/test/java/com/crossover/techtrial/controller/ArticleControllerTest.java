package com.crossover.techtrial.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.service.ArticleService;
import com.crossover.techtrial.service.ArticleTestFixtureUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest
{

	@MockBean
	private ArticleService articleService;

	@Autowired
	private TestRestTemplate template;

	@Test
	public void should_CreateArticle() throws Exception
	{
		// given
		Article createdArticle = ArticleTestFixtureUtils.anyArticle();
		when(articleService.save(ArticleTestFixtureUtils
			.articleWithEmail("user1@gmail.com")))
				.thenReturn(createdArticle);
		HttpEntity<Object> articleRequest = getHttpEntity(
			"{\"email\": \"user1@gmail.com\" }");

		// when
		ResponseEntity<Article> responseEntity = template.postForEntity(
			"/articles", articleRequest, Article.class);

		// then
		assertThat(responseEntity.getStatusCode())
			.isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isEqualTo(createdArticle);
	}

	@Test
	public void should_DeleteArticle() throws Exception
	{
		// given
		Long id = anyId();

		// when
		ResponseEntity<Void> responseEntity = template.exchange(
			"/articles/{id}", HttpMethod.DELETE, null, Void.class,
			Collections.singletonMap("id", id));

		// then
		verify(articleService, times(1)).delete(id);
		assertThat(responseEntity.getStatusCode())
			.isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNull();
	}

	@Test
	public void should_GetExistingArticle() throws Exception
	{
		// given
		Long id = anyId();
		Article article = ArticleTestFixtureUtils.anyArticle();
		when(articleService.findById(id))
			.thenReturn(Optional.of(article));

		// when
		ResponseEntity<Article> responseEntity = template.getForEntity(
			"/articles/{id}", Article.class,
			Collections.singletonMap("id", id));

		// then
		assertThat(responseEntity.getStatusCode())
			.isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isEqualTo(article);
	}

	@Test
	public void should_ReturnNotFoundStatus_When_GetNonExistentArticle()
		throws Exception
	{
		// given
		Long id = anyId();
		when(articleService.findById(id)).thenReturn(Optional.empty());

		// when
		ResponseEntity<Article> responseEntity = template.getForEntity(
			"/articles/{id}", Article.class,
			Collections.singletonMap("id", id));

		// then
		assertThat(responseEntity.getStatusCode())
			.isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(responseEntity.getBody()).isNull();
	}

	@Test
	public void should_ReturnNotFoundStatus_When_UpdateNonExistentArticle()
		throws Exception
	{
		// given
		Long id = anyId();
		Article updatedArticle = ArticleTestFixtureUtils
			.articleWithEmail("user1@gmail.com");
		when(articleService.update(id, updatedArticle))
			.thenReturn(false);

		// when
		ResponseEntity<Void> responseEntity = template.exchange(
			"/articles/{id}", HttpMethod.PUT,
			getHttpEntity("{\"email\": \"user1@gmail.com\" }"),
			Void.class, Collections.singletonMap("id", id));

		// then
		assertThat(responseEntity.getStatusCode())
			.isEqualTo(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void should_SearchArticles() throws Exception
	{
		// given
		String title = anyTitle();
		Article matchingArticle = ArticleTestFixtureUtils.anyArticle();
		when(articleService.search(title))
			.thenReturn(Arrays.asList(matchingArticle));

		// when
		ResponseEntity<List<Article>> responseEntity = template
			.exchange("/articles/search?text={text}",
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Article>>()
				{
				}, Collections.singletonMap("text", title));

		// then
		assertThat(responseEntity.getStatusCode())
			.isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody())
			.containsOnly(matchingArticle);
	}

	@Test
	public void should_UpdateExistingArticle() throws Exception
	{
		// given
		Long id = anyId();
		Article updatedArticle = ArticleTestFixtureUtils
			.articleWithEmail("user1@gmail.com");
		when(articleService.update(id, updatedArticle))
			.thenReturn(true);

		// when
		ResponseEntity<Void> responseEntity = template.exchange(
			"/articles/{id}", HttpMethod.PUT,
			getHttpEntity("{\"email\": \"user1@gmail.com\" }"),
			Void.class, Collections.singletonMap("id", id));

		// then
		verify(articleService, times(1)).update(id, updatedArticle);
		assertThat(responseEntity.getStatusCode())
			.isEqualTo(HttpStatus.OK);
	}

	/**
	 * @return
	 */
	private Long anyId()
	{
		return 0L;
	}

	/**
	 * @return
	 */
	private String anyTitle()
	{
		return "Introduction to DDD";
	}

	private HttpEntity<Object> getHttpEntity(Object body)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}
}
