package com.webburguer.burguer3j.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webburguer.burguer3j.entity.Sugerencias;


@Repository
public interface SugerenciasRepository extends CrudRepository<Sugerencias, Long> {
	
	public Optional<Sugerencias> findByAsunto(String asunto);

 }