package com.webburguer.burguer3j.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webburguer.burguer3j.entity.Role;



@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
	
	public Optional<Role> findById(Long id);

}