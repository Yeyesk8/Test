package com.webburguer.burguer3j.service;

import com.webburguer.burguer3j.Exception.SandwichNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Sandwich;


public interface SandwichService {
	
	public Iterable<Sandwich> getAllSandwiches();
	
	public Sandwich createSandwich(Sandwich sandwich) throws Exception;
	
	public Sandwich getSandwichById(Long id) throws Exception;

	public Sandwich updateSandwich(Sandwich baguette) throws Exception;
	
	public void borrarSandwich(Long id) throws SandwichNameOrIdNotFound;
	
	

}
