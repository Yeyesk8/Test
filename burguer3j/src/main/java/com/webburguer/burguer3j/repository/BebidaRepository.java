
package com.webburguer.burguer3j.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webburguer.burguer3j.entity.Bebida;

@Repository
public interface BebidaRepository extends CrudRepository<Bebida, Long> {
	
	public Optional<Bebida> findByName(String name);

}