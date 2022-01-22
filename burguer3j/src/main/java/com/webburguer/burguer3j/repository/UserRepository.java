package com.webburguer.burguer3j.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webburguer.burguer3j.entity.User;



@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	public Optional<User> findByUsername(String username);

 }