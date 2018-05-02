/**
 *
 */
package com.crossover.techtrial.service;

/**
 * This interface is added to demonstrate how we could scale articles
 * application using event-driven architecture.
 *
 * @author goobar
 *
 */
public interface EventPublisher<T>
{
	/**
	 * Publishes an event. Event might be consumed e.g. by another node
	 * using such technologies as RabbitMq or any other message broker.
	 *
	 * @param event
	 */
	void publishEvent(T event);
}
