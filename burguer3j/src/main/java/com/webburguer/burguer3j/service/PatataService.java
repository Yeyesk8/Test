package com.webburguer.burguer3j.service;

import com.webburguer.burguer3j.Exception.PatataNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Patata;


public interface PatataService {
	
	public Iterable<Patata> getAllPatatas();
	
	public Patata createPatata(Patata baguette) throws Exception;
	
	public Patata getPatataById(Long id) throws PatataNameOrIdNotFound;

	public Patata updatePatata(Patata baguette) throws Exception;
	
	public void borrarPatata(Long id) throws PatataNameOrIdNotFound;
	
	

}
