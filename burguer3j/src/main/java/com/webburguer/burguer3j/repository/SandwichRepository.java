package com.webburguer.burguer3j.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webburguer.burguer3j.entity.Sandwich;


@Repository
public interface SandwichRepository extends CrudRepository<Sandwich, Long> {
	
	public Optional<Sandwich> findByName(String name);

}