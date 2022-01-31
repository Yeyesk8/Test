package com.webburguer.burguer3j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.webburguer.burguer3j.Exception.PatataNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Patata;
import com.webburguer.burguer3j.repository.PatataRepository;

@Service
public class PatataServiceImpl implements PatataService {
	
	@Autowired
	PatataRepository parepository;


	@Override
	public Iterable<Patata> getAllPatatas() {
		return parepository.findAll();
	}
	
	private boolean checkNameAvailable(Patata patata) throws Exception {
		Optional<Patata> patataFound = parepository.findByName(patata.getName());
		if (patataFound.isPresent()) {
			throw new Exception("Patata no disponible");
		}
		return true;
	}
	


	@Override
	public Patata createPatata(Patata patata) throws Exception {
		if (checkNameAvailable(patata)) {
			patata = parepository.save(patata);
		}
		return patata;
	}

	@Override
	public Patata getPatataById(Long id) throws PatataNameOrIdNotFound {
		return parepository.findById(id).orElseThrow(() -> new PatataNameOrIdNotFound("El Id de la Baguette no existe."));
	}

	@Override
	public Patata updatePatata(Patata fromPatata) throws Exception {
		Patata toPatata = getPatataById(fromPatata.getId());
		mapPatata(fromPatata, toPatata);
		return parepository.save(toPatata);
	}

	protected void mapPatata(Patata from, Patata to) {
		to.setId(from.getId());
		to.setName(from.getName());
		to.setGluten(from.getGluten());
		to.setTamanio_plato(from.getTamanio_plato());
		to.setPrecio(from.getPrecio());
		to.setDescripcion(from.getDescripcion());
		to.setImagen(from.getImagen());
		
	}

	@Override
	public void borrarPatata(Long id) throws PatataNameOrIdNotFound {
		Patata patata = getPatataById(id);
		parepository.delete(patata);
	}

	@Override
	public Optional<Patata> get(Long id) {
		
		return parepository.findById(id);
	}


}
