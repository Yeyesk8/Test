package com.webburguer.burguer3j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webburguer.burguer3j.Exception.SandwichNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Sandwich;
import com.webburguer.burguer3j.repository.SandwichRepository;

@Service
public class SandwichServiceImpl implements SandwichService {
	
	@Autowired
	SandwichRepository sarepository;


	@Override
	public Iterable<Sandwich> getAllSandwiches(){
		return sarepository.findAll();
	}
	
	private boolean checkNameAvailable(Sandwich sandwich) throws Exception {
		Optional<Sandwich> sandwichFound = sarepository.findByName(sandwich.getName());
		if (sandwichFound.isPresent()) {
			throw new Exception("Sandwich no disponible");
		}
		return true;
	}
	


	@Override
	public Sandwich createSandwich(Sandwich sandwich) throws Exception {
		if (checkNameAvailable(sandwich)) {
			sandwich = sarepository.save(sandwich);
		}
		return sandwich;
	}

	@Override
	public Sandwich getSandwichById(Long id) throws SandwichNameOrIdNotFound {
		return sarepository.findById(id).orElseThrow(() -> new SandwichNameOrIdNotFound("El Id del Sandwich no existe."));
	}

	@Override
	public Sandwich updateSandwich(Sandwich fromSandwich) throws Exception {
		Sandwich toSandwich = getSandwichById(fromSandwich.getId());
		mapSandwich(fromSandwich, toSandwich);
		return sarepository.save(toSandwich);
	}

	protected void mapSandwich(Sandwich from, Sandwich to) {
		to.setName(from.getName());
		to.setGluten(from.getGluten());
		to.setBacon(from.getBacon());
		to.setLechuga(from.getLechuga());
		to.setTomate(from.getTomate());
		to.setEsparragos(from.getEsparragos());
		to.setTipo_huevo(from.getTipo_huevo());
		to.setTipo_queso(from.getTipo_queso());
		to.setYork(from.getYork());
		to.setMahonesa(from.getMahonesa());
		to.setPrecio(from.getPrecio());
		to.setDescripcion(from.getDescripcion());
		to.setImagen(from.getImagen());
	}

	@Override
	public void borrarSandwich(Long id) throws SandwichNameOrIdNotFound {
		Sandwich sandwich = getSandwichById(id);
		sarepository.delete(sandwich);
	}

	@Override
	public Optional<Sandwich> get(Long id) {
		return sarepository.findById(id);
	}


}
