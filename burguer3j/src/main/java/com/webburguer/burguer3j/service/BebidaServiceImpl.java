
package com.webburguer.burguer3j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.webburguer.burguer3j.Exception.BebidaNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Bebida;
import com.webburguer.burguer3j.repository.BebidaRepository;


@Service
public class BebidaServiceImpl implements BebidaService {
	
	@Autowired
	BebidaRepository berepository;


	@Override
	public Iterable<Bebida> getAllBebidas() {
		return berepository.findAll();
	}
	
	private boolean checkNameAvailable(Bebida bebida) throws Exception {
		Optional<Bebida> bebidaFound = berepository.findByName(bebida.getName());
		if (bebidaFound.isPresent()) {
			throw new Exception("Bebida no disponible");
		}
		return true;
	}
	

	@Override
	public Bebida createBebida(Bebida bebida) throws Exception {
		if (checkNameAvailable(bebida)) {
			bebida = berepository.save(bebida);
		}
		return bebida;
	}

	@Override
	public Bebida getBebidaById(Long id) throws BebidaNameOrIdNotFound {
		return berepository.findById(id).orElseThrow(() -> new BebidaNameOrIdNotFound("El Id de la Bebida no existe."));
	}

	@Override
	public Bebida updateBebida(Bebida fromBebida) throws Exception {
		Bebida toBebida = getBebidaById(fromBebida.getId());
		mapBebida(fromBebida, toBebida);
		return berepository.save(toBebida);
	}

	protected void mapBebida(Bebida from, Bebida to) {
		to.setName(from.getName());
		to.setAlcohol(from.getAlcohol());
		to.setTipo_vaso(from.getTipo_vaso());
	}

	@Override
	public void borrarBebida(Long id) throws BebidaNameOrIdNotFound {
		Bebida bebida = getBebidaById(id);
		berepository.delete(bebida);
	}


}
