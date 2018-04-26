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
import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.service.CommentFixtureUtils;

/**
 * @author goobar
 *
 */
@RunWith(SpringRunner.class)
@JsonTest
@SuppressWarnings("javadoc")
public class CommentJsonIntegrationTest
{
	@Autowired
	private JacksonTester<Comment> jsonTester;

	@Test
	public void should_DeserializeAllProperties() throws Exception
	{
		// given
		String commentJson =
			// begin
			"{"
				// date
				+ "    \"date\": \"2010-10-13T20:35:30\", "
				// email
				+ "    \"email\": \"test@email.com\", "
				// id
				+ "    \"id\": 777, "
				// message
				+ "    \"message\": \"This article is awesome!\" "
				// end
				+ "}";
		// expected comment
		Comment expectedComment = CommentFixtureUtils.builder()
			.withDate(LocalDateTime.of(2010, 10, 13, 20, 35, 30))
			.withEmail("test@email.com").withId(777L)
			.withMessage("This article is awesome!").build();

		// when
		ObjectContent<Comment> content = jsonTester.parse(commentJson);

		// then
		assertThat(content)
			.isEqualToComparingFieldByField(expectedComment);
	}

	@Test
	public void should_SerializeAllProperties() throws Exception
	{
		// given
		Comment comment = CommentFixtureUtils.builder()
			.withDate(LocalDateTime.of(2010, 10, 13, 20, 35, 30))
			.withEmail("test@email.com").withId(777L)
			.withMessage("This article is awesome!").build();

		// when
		JsonContent<Comment> jsonContent = jsonTester.write(comment);

		// then
		assertThat(jsonContent).extractingJsonPathValue("$.date")
			.isEqualTo("2010-10-13T20:35:30");
		assertThat(jsonContent).extractingJsonPathValue("$.email")
			.isEqualTo("test@email.com");
		assertThat(jsonContent).extractingJsonPathValue("$.id")
			.isEqualTo(777);
		assertThat(jsonContent).extractingJsonPathValue("$.message")
			.isEqualTo("This article is awesome!");
	}

}
