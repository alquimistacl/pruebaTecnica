package com.prueba.tecnica.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class QueueEvent {
	private String message;

	private Logger logger = LogManager.getLogger(QueueEvent.class);

	public QueueEvent(String message) {
		this.message = message;
		logger.info(message);
	}

}
