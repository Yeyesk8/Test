package com.webburguer.burguer3j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webburguer.burguer3j.entity.Sugerencias;
import com.webburguer.burguer3j.repository.SugerenciasRepository;


@Service
public class SugerenciasServiceImpl implements SugerenciasService {

	@Autowired
	SugerenciasRepository surepository;

	

	@Override
	public Iterable<Sugerencias> getAllSugerencias() {
		return surepository.findAll();
	}

	private boolean checkIdAvailable(Sugerencias sugerencias) throws Exception {
		Optional<Sugerencias> sugerFound = surepository.findById(sugerencias.getId());
		if (sugerFound.isPresent()) {
			throw new Exception("Sugerencia no disponible");
		}
		return true;
	}


	@Override
	public Sugerencias createSugerencia(Sugerencias sugerencias) throws Exception {
		if (checkIdAvailable(sugerencias)) {
			sugerencias = surepository.save(sugerencias);
		}
		return sugerencias;
	}

	@Override
	public Sugerencias getSugerenciaById(Long id) throws Exception  {
		return surepository.findById(id).orElseThrow(() ->  new Exception(" No existe la sugerencia"));
	}
	
	protected void mapSugerencia(Sugerencias from, Sugerencias to) {
		to.setFullname(from.getFullname());
		to.setEmail(from.getEmail());
		to.setMensaje(from.getMensaje());
	}

	
	@Override
	public void borrarSugerencia(Long id) throws Exception {
		Sugerencias sugerencias = getSugerenciaById(id);
		surepository.delete(sugerencias);
	}


}