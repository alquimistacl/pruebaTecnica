package com.prueba.tecnica.exception;

/**
 * Exception to handle when the table was not found
 * @author Luis San Martin
 *
 */
public class TableNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8693762947778269609L;

	public TableNotFoundException(String message) {
		super(message);
	}
}
