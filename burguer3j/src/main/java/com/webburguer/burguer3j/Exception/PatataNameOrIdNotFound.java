package com.webburguer.burguer3j.Exception;

public class PatataNameOrIdNotFound extends Exception {

	
	private static final long serialVersionUID = -7680859476987101715L;

	public PatataNameOrIdNotFound() {
		super("Nombre o Id no encontrado");
	}

	public PatataNameOrIdNotFound(String message) {
		super(message);
	}
}