
package com.webburguer.burguer3j.service;


import java.util.List;
import java.util.Optional;

import com.webburguer.burguer3j.entity.Orden;
import com.webburguer.burguer3j.entity.User;

public interface OrdenService {
	
	List<Orden> findAll();
	
	Optional<Orden> findById(Long id);
	
	String generarNumeroOrden();
	
	Orden createOrden(Orden orden);
	
	List<Orden> findByUsuario (User usuario);

	
}