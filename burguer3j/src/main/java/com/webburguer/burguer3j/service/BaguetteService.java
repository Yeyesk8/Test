package com.webburguer.burguer3j.service;

import com.webburguer.burguer3j.Exception.BaguetteNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Baguette;


public interface BaguetteService {
	
	public Iterable<Baguette> getAllBaguettes();
	
	public Baguette createBaguette(Baguette baguette) throws Exception;
	
	public Baguette getBaguetteById(Long id) throws Exception;

	public Baguette updateBaguette(Baguette baguette) throws Exception;
	
	public void borrarBaguette(Long id) throws BaguetteNameOrIdNotFound;
	
	

}
