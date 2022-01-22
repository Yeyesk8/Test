package com.webburguer.burguer3j.Exception;

public class BurguerNameOrIdNotFound extends Exception {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6356830748973328084L;

	public BurguerNameOrIdNotFound() {
		super("Nombre o Id no encontrado");
	}

	public BurguerNameOrIdNotFound(String message) {
		super(message);
	}
}