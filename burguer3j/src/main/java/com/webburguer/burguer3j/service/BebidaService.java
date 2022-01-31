
package com.webburguer.burguer3j.service;

import java.util.Optional;

import com.webburguer.burguer3j.Exception.BebidaNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Bebida;


public interface BebidaService {
	
	public Iterable<Bebida> getAllBebidas();
	
	public Optional<Bebida> get(Long id);
	
	public Bebida createBebida(Bebida bebida) throws Exception;
	
	public Bebida getBebidaById(Long id) throws BebidaNameOrIdNotFound;

	public Bebida updateBebida(Bebida bebida) throws Exception;
	
	public void borrarBebida(Long id) throws BebidaNameOrIdNotFound;
	
	

}
