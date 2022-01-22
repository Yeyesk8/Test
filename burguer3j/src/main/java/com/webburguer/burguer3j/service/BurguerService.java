package com.webburguer.burguer3j.service;


import com.webburguer.burguer3j.Exception.BurguerNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Burguer;


public interface BurguerService {
	
	public Iterable<Burguer> getAllBurguers();
	
	public Burguer createBurguer(Burguer burguer) throws Exception;
	
	public Burguer getBurguerById(Long id) throws BurguerNameOrIdNotFound;

	public Burguer updateBurguer(Burguer burguer) throws Exception;
	
	public void borrarBurguer(Long id) throws BurguerNameOrIdNotFound;
	
	

}
