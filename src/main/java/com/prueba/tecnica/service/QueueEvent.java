package com.prueba.tecnica.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Event to record when the {@link MockQueue} is called
 * @author Luis San Martin
 *
 */
public class QueueEvent {
	private final String message;

	private Logger logger = LogManager.getLogger(QueueEvent.class);

	public QueueEvent(String message) {
		this.message = message;
		logger.info(message);
	}

	public String getMessage() {
		return message;
	}

}
