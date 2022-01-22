package com.webburguer.burguer3j.Exception;

public class BaguetteNameOrIdNotFound extends Exception {

	
	private static final long serialVersionUID = 4224102531636392135L;

	public BaguetteNameOrIdNotFound() {
		super("Nombre o Id no encontrado");
	}

	public BaguetteNameOrIdNotFound(String message) {
		super(message);
	}
}