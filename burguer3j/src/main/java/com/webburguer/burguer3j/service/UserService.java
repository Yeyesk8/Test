package com.webburguer.burguer3j.service;


import java.util.Optional;

import com.webburguer.burguer3j.Exception.UsernameOrIdNotFound;
import com.webburguer.burguer3j.dto.ChangePasswordForm;
import com.webburguer.burguer3j.entity.User;

public interface UserService {

	public Iterable<User> getAllUsers();
	
	public User createUser(User user) throws Exception;
	
	public User getUserById(Long id) throws UsernameOrIdNotFound;

	public User updateUser(User user) throws Exception;
	
	public void borrarUsuario(Long id) throws UsernameOrIdNotFound;
	
	public User changePassword(ChangePasswordForm form) throws Exception;
	
	public User getLoggedUser() throws Exception;
	
	Optional<User> findById(Long id);
	
}
