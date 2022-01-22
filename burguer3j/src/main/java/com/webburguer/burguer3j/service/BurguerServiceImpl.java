package com.webburguer.burguer3j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webburguer.burguer3j.Exception.BurguerNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Burguer;
import com.webburguer.burguer3j.repository.BurguerRepository;

@Service
public class BurguerServiceImpl implements BurguerService {
	
	@Autowired
	BurguerRepository brepository;


	@Override
	public Iterable<Burguer> getAllBurguers() {
		return brepository.findAll();
	}
	
	private boolean checkNameAvailable(Burguer burguer) throws Exception {
		Optional<Burguer> burguerFound = (Optional<Burguer>) brepository.findByName(burguer.getName());
		if (burguerFound.isPresent()) {
			throw new Exception("Hamburguesa no disponible");
		}
		return true;
	}
	

	@Override
	public Burguer createBurguer(Burguer burguer) throws Exception {
		if (checkNameAvailable(burguer)) {
			burguer = brepository.save(burguer);
		}
		return burguer;
	}

	@Override
	public Burguer getBurguerById(Long id) throws BurguerNameOrIdNotFound {
		return brepository.findById(id).orElseThrow(() -> new BurguerNameOrIdNotFound("El Id de la Hamburguesa no existe."));
	}

	@Override
	public Burguer updateBurguer(Burguer fromBurguer) throws Exception {
		Burguer toBurguer = getBurguerById(fromBurguer.getId());
		mapBurguer(fromBurguer, toBurguer);
		return brepository.save(toBurguer);
	}

	protected void mapBurguer(Burguer from, Burguer to) {
		to.setName(from.getName());
		to.setTipo_carne(from.getTipo_carne());
		to.setGluten(from.getGluten());
		to.setTipo_queso(from.getTipo_queso());
		to.setLechuga(from.getLechuga());
		to.setCebolla(from.getCebolla());
		to.setTipo_huevo(from.getTipo_huevo());
		to.setBacon(from.getBacon());
		to.setPepinillo(from.getPepinillo());
		to.setTomate(from.getTomate());
		to.setYork(from.getYork());
		
	}
	@Override
	public void borrarBurguer(Long id) throws BurguerNameOrIdNotFound {
		Burguer burguer = getBurguerById(id);
		brepository.delete(burguer);
	}


}
