/**
 *
 */
package com.crossover.techtrial.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ControllerTestUtils
{

	/**
	 * @param body
	 * @return
	 */
	public static HttpEntity<Object> getHttpEntity(Object body)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}

}
