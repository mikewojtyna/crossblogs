package com.crossover.techtrial.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.service.ArticleFixtureUtils;
import com.crossover.techtrial.service.ArticleService;
import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.revinate.assertj.json.JsonPathAssert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SuppressWarnings("javadoc")
public class ArticleControllerIntegrationTest
{

	@MockBean
	private ArticleService articleService;

	@Autowired
	private TestRestTemplate template;

	@Test
	public void should_CreateArticle() throws Exception
	{
		// given
		Article createdArticle = ArticleFixtureUtils.anyArticle();
		when(articleService.save(ArticleFixtureUtils
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
		Article article = ArticleFixtureUtils.anyArticle();
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
		Article updatedArticle = ArticleFixtureUtils
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

	@Test
	public void should_SearchArticles() throws Exception
	{
		// given
		String title = anyTitle();
		Article matchingArticle = ArticleFixtureUtils.anyArticle();
		Pageable pageRequest = PageRequest.of(5, 100);
		Page<Article> responsePage = new PageImpl<>(
			Arrays.asList(matchingArticle), pageRequest, 1000);
		when(articleService.search(title, pageRequest))
			.thenReturn(responsePage);

		// when
		ResponseEntity<String> responseEntity = template.exchange(
			"/articles/search?text={text}&page={page}&size={size}",
			HttpMethod.GET, null, String.class, ImmutableMap
				.of("text", title, "page", "5", "size", "100"));

		// then
		assertThat(responseEntity.getStatusCode())
			.isEqualTo(HttpStatus.OK);
		DocumentContext body = JsonPath.parse(responseEntity.getBody());

		assertJson(body).jsonPathAsString("$.number").isEqualTo("5");
		assertJson(body).jsonPathAsString("$.numberOfElements")
			.isEqualTo("1");
		assertJson(body).jsonPathAsString("$.totalElements")
			.isEqualTo("1000");
		assertJson(body).jsonPathAsString("$.size").isEqualTo("100");
		assertJson(body).jsonPathAsString("$.content[0].title")
			.isEqualTo(matchingArticle.getTitle());
	}

	@Test
	public void should_UpdateExistingArticle() throws Exception
	{
		// given
		Long id = anyId();
		Article updatedArticle = ArticleFixtureUtils
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

	/**
	 * @param body
	 * @return
	 */
	private JsonPathAssert assertJson(DocumentContext body)
	{
		return com.revinate.assertj.json.JsonPathAssert
			.assertThat(body);
	}

	private HttpEntity<Object> getHttpEntity(Object body)
	{
		return ControllerTestUtils.getHttpEntity(body);
	}
}
