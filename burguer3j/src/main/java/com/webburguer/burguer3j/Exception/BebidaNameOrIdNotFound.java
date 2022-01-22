
package com.webburguer.burguer3j.Exception;

public class BebidaNameOrIdNotFound extends Exception {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4022098191810505040L;

	public BebidaNameOrIdNotFound() {
		super("Nombre o Id no encontrado");
	}

	public BebidaNameOrIdNotFound(String message) {
		super(message);
	}
}