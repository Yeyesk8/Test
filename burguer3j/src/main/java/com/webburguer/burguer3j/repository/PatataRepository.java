package com.webburguer.burguer3j.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webburguer.burguer3j.entity.Patata;

@Repository
public interface PatataRepository extends CrudRepository<Patata, Long> {
	
	public Optional<Patata> findByName(String name);

}