/**
 *
 */
package com.crossover.techtrial.service;

import org.springframework.stereotype.Service;

/**
 * A dummy implementation just to demonstrate the approach and avoid exceptions
 * when starting the application.
 *
 * @author goobar
 *
 */
@Service
public class DummyEventPublisher implements EventPublisher<ArticleEvent>
{

	/* (non-Javadoc)
	 * @see com.crossover.techtrial.service.EventPublisher#publishEvent(java.lang.Object)
	 */
	@Override
	public void publishEvent(ArticleEvent event)
	{
		// do nothing - it's here just to demonstrate the idea
	}

}
