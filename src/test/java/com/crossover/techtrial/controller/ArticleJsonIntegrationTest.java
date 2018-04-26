/**
 *
 */
package com.crossover.techtrial.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.test.context.junit4.SpringRunner;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.service.ArticleFixtureUtils;

/**
 * @author goobar
 *
 */
@RunWith(SpringRunner.class)
@JsonTest
@SuppressWarnings("javadoc")
public class ArticleJsonIntegrationTest
{

	@Autowired
	private JacksonTester<Article> jsonTester;

	@Test
	public void should_DeserializeArticle_With_AllProperties()
		throws Exception
	{
		// given
		String articleJson =
			// begin json
			"{"
				// content property
				+ "\"content\": \"Content of the article\","
				// date property
				+ "\"date\": \"2010-10-13T20:35:30\","
				// email property
				+ "\"email\": \"user1@gmail.com\","
				// id property
				+ "\"id\": 777,"
				// published property
				+ "\"published\": true,"
				// title property
				+ "\"title\": \"Introduction to DDD\""
				// end json
				+ " }";
		// expected article
		Article expectedArticle = ArticleFixtureUtils.builder()
			.withContent("Content of the article")
			.withDate(LocalDateTime.of(2010, 10, 13, 20, 35, 30))
			.withEmail("user1@gmail.com").withId(777L)
			.withPublished(true).withTitle("Introduction to DDD")
			.build();

		// when
		ObjectContent<Article> article = jsonTester.parse(articleJson);

		// then
		assertThat(article)
			.isEqualToComparingFieldByField(expectedArticle);
	}

	@Test
	public void should_SerializeArticle_With_AllProperties()
		throws Exception
	{
		// given
		Article article = ArticleFixtureUtils.builder()
			.withContent("Content of the article")
			.withDate(LocalDateTime.of(2010, 10, 13, 20, 35, 30))
			.withEmail("user1@gmail.com").withId(777L)
			.withPublished(true).withTitle("Introduction to DDD")
			.build();

		// when
		JsonContent<Article> articleJson = jsonTester.write(article);

		// then
		assertThat(articleJson).extractingJsonPathValue("$.content")
			.isEqualTo("Content of the article");
		assertThat(articleJson).extractingJsonPathValue("$.date")
			.isEqualTo("2010-10-13T20:35:30");
		assertThat(articleJson).extractingJsonPathValue("$.email")
			.isEqualTo("user1@gmail.com");
		assertThat(articleJson).extractingJsonPathValue("$.id")
			.isEqualTo(777);
		assertThat(articleJson)
			.extractingJsonPathBooleanValue("$.published").isTrue();
		assertThat(articleJson).extractingJsonPathValue("$.title")
			.isEqualTo("Introduction to DDD");
	}

}
