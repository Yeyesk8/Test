package com.webburguer.burguer3j.service;


import com.webburguer.burguer3j.entity.Sugerencias;


public interface SugerenciasService {

	public Iterable<Sugerencias> getAllSugerencias();
	
	public Sugerencias createSugerencia(Sugerencias sugerencias) throws Exception;
	
	public Sugerencias getSugerenciaById(Long id) throws Exception;

	public void borrarSugerencia(Long id) throws Exception ;
	
}
