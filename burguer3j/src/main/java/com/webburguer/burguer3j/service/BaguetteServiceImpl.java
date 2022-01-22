package com.webburguer.burguer3j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.webburguer.burguer3j.Exception.BaguetteNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Baguette;
import com.webburguer.burguer3j.repository.BaguetteRepository;

@Service
public class BaguetteServiceImpl implements BaguetteService {
	
	@Autowired
	BaguetteRepository barepository;


	@Override
	public Iterable<Baguette> getAllBaguettes() {
		return barepository.findAll();
	}
	
	private boolean checkNameAvailable(Baguette baguette) throws Exception {
		Optional<Baguette> burguerFound = barepository.findByName(baguette.getName());
		if (burguerFound.isPresent()) {
			throw new Exception("Baguette no disponible");
		}
		return true;
	}
	

	@Override
	public Baguette createBaguette(Baguette baguette) throws Exception {
		if (checkNameAvailable(baguette)) {
			baguette = barepository.save(baguette);
		}
		return baguette;
	}

	@Override
	public Baguette getBaguetteById(Long id) throws BaguetteNameOrIdNotFound {
		return barepository.findById(id).orElseThrow(() -> new BaguetteNameOrIdNotFound("El Id de la Baguette no existe."));
	}

	@Override
	public Baguette updateBaguette(Baguette fromBaguette) throws Exception {
		Baguette toBaguette = getBaguetteById(fromBaguette.getId());
		mapBaguette(fromBaguette, toBaguette);
		return barepository.save(toBaguette);
	}

	protected void mapBaguette(Baguette from, Baguette to) {
		to.setName(from.getName());
		to.setContenido(from.getContenido());
		to.setTipo_queso(from.getTipo_queso());
		to.setLechuga(from.getLechuga());
		to.setTomate(from.getTomate());
		to.setMahonesa(from.getMahonesa());
	}

	@Override
	public void borrarBaguette(Long id) throws BaguetteNameOrIdNotFound {
		Baguette baguette = getBaguetteById(id);
		barepository.delete(baguette);
	}


}
