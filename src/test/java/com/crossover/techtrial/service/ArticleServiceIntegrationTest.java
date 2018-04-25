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
import com.crossover.techtrial.repository.ArticleRepository;

/**
 * @author goobar
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
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
		assertThat(findArticle(savedArticle.getId()))
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
		assertThat(findArticle(article.getId())).isNull();
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

		// when
		List<Article> matchingArticles = articleService.search(title);

		// then
		assertThat(matchingArticles).hasSize(1);
		assertThat(matchingArticles.iterator().next().getTitle())
			.isEqualTo(title);
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
	private Article findArticle(Long id)
	{
		return articleService.findById(id);
	}

}