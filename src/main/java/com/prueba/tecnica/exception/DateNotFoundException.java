package com.prueba.tecnica.exception;

/**
 * Exception to handle when the date does not have info
 * 
 * @author Luis San Martin
 *
 */
public class DateNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5335628848473818648L;

	public DateNotFoundException(String message) {
		super(message);
	}
}
