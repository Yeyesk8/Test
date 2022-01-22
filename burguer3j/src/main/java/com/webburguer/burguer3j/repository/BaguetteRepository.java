package com.webburguer.burguer3j.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webburguer.burguer3j.entity.Baguette;

@Repository
public interface BaguetteRepository extends CrudRepository<Baguette, Long> {
	
	public Optional<Baguette> findByName(String name);

}