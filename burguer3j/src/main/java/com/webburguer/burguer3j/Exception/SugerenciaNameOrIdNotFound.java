package com.webburguer.burguer3j.Exception;

public class SugerenciaNameOrIdNotFound extends Exception {

	


	private static final long serialVersionUID = 5212309658147239079L;

	public SugerenciaNameOrIdNotFound() {
		super("Sugerencia o Id no encontrado");
	}

	public SugerenciaNameOrIdNotFound(String message) {
		super(message);
	}
}