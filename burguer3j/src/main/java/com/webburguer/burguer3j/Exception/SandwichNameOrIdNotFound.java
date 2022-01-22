package com.webburguer.burguer3j.Exception;

public class SandwichNameOrIdNotFound extends Exception {

	
	private static final long serialVersionUID = 4224102531636392135L;

	public SandwichNameOrIdNotFound() {
		super("Nombre o Id no encontrado");
	}

	public SandwichNameOrIdNotFound(String message) {
		super(message);
	}
}

