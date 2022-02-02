package com.webburguer.burguer3j.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webburguer.burguer3j.entity.Orden;
import com.webburguer.burguer3j.entity.User;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
	
	List<Orden> findByUsuario (User usuario);

}