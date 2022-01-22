package com.webburguer.burguer3j.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webburguer.burguer3j.entity.Burguer;

@Repository
public interface BurguerRepository extends CrudRepository<Burguer, Long> {
	
	public Optional<Burguer> findByName(String name);

}
