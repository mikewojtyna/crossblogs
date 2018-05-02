/**
 *
 */
package com.crossover.techtrial.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.repository.ArticleRepository;

/**
 * @author goobar
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("javadoc")
public class ArticleServiceIntegrationTest
{

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleService articleService;

	@After
	public void after()
	{
		cleanDb();
	}

	@Test
	public void should_CreateArticle() throws Exception
	{
		// given
		String email = anyEmail();
		Article givenArticle = articleWithEmail(email);

		// when
		Article savedArticle = articleService.save(givenArticle);

		// then
		assertThat(savedArticle.getEmail())
			.isEqualTo(givenArticle.getEmail());
		assertThat(findArticle(savedArticle.getId()).get())
			.isEqualTo(savedArticle);
	}

	@Test
	public void should_DeleteArticle() throws Exception
	{
		// given
		Article article = createArticle();

		// when
		articleService.delete(article.getId());

		// then
		assertThat(findArticle(article.getId())).isNotPresent();
	}

	@Test
	public void should_NotUpdate_NonExistentArticle() throws Exception
	{
		// given
		Article updatedArticle = ArticleFixtureUtils.anyArticle();
		Long nonExistentId = -1L;

		// when
		boolean updated = articleService.update(nonExistentId,
			updatedArticle);

		// then
		assertThat(updated).isFalse();
		assertThat(findArticle(nonExistentId)).isNotPresent();
	}

	@Test
	public void should_ReturnEmptyOptional_When_FindArticleThatDoesntExist()
		throws Exception
	{
		assertThat(articleService.findById(-1L)).isEmpty();
	}

	@Test
	public void should_SearchForArticle() throws Exception
	{
		// given
		String title = "Software Craftsmanship";
		// create some articles in db
		createArticleWithTitle("Pragmatic TDD");
		createArticleWithTitle("Integration tests with Docker");
		createArticleWithTitle(title);
		createArticleWithTitle("Introduction to DDD");
		createArticleWithTitle(
			"Why the architecture of this app sucks");
		Pageable pageRequest = PageRequest.of(0, 10);

		// when
		Page<Article> matchingArticles = articleService.search(title,
			pageRequest);

		// then
		assertThat(matchingArticles).hasSize(1);
		assertThat(matchingArticles.iterator().next().getTitle())
			.isEqualTo(title);
	}

	@Test
	public void should_UpdateExistingArticle() throws Exception
	{
		// given
		Article updatedArticle = ArticleFixtureUtils
			.articleWithTitle("Introduction to DDD");
		// create some existing articles
		Article tddArticle = createArticleWithTitle("TDD basics");
		createArticleWithTitle("Kotlin");
		createArticleWithTitle("Scala examples");

		// when
		boolean updated = articleService.update(tddArticle.getId(),
			updatedArticle);

		// then
		assertThat(updated).isTrue();
		assertThat(findArticle(tddArticle.getId()).get().getTitle())
			.isEqualTo("Introduction to DDD");
	}

	/**
	 * @return
	 */
	private String anyEmail()
	{
		return "any@email.com";
	}

	/**
	 * @return
	 */
	private String anyTitle()
	{
		return "Article Title";
	}

	/**
	 * @param email
	 * @return
	 */
	private Article articleWithEmail(String email)
	{
		Article article = new Article();
		article.setEmail(email);
		return article;
	}

	/**
	 * @param title
	 * @return
	 */
	private Article articleWithTitle(String title)
	{
		Article article = articleWithEmail(anyEmail());
		article.setTitle(title);
		return article;
	}

	/**
	 *
	 */
	private void cleanDb()
	{
		articleRepository.deleteAll();
	}

	/**
	 * @return
	 *
	 */
	private Article createArticle()
	{
		return createArticleWithTitle(anyTitle());
	}

	/**
	 * @param title
	 * @return
	 */
	private Article createArticleWithTitle(String title)
	{
		return articleService.save(articleWithTitle(title));
	}

	/**
	 * @param id
	 * @return
	 */
	private Optional<Article> findArticle(Long id)
	{
		return articleService.findById(id);
	}

}
