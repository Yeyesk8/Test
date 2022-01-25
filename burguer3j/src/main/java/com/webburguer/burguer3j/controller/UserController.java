package com.webburguer.burguer3j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.stream.Collectors;

import javax.validation.Valid;

import com.webburguer.burguer3j.Exception.UsernameOrIdNotFound;
import com.webburguer.burguer3j.dto.ChangePasswordForm;
import com.webburguer.burguer3j.entity.Role;
import com.webburguer.burguer3j.entity.Sugerencias;
import com.webburguer.burguer3j.entity.User;
import com.webburguer.burguer3j.repository.RoleRepository;
import com.webburguer.burguer3j.service.BurguerService;
import com.webburguer.burguer3j.service.SugerenciasService;
import com.webburguer.burguer3j.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	BurguerService burguerservice;

	@Autowired
	SugerenciasService sugerenciasService;

	@Autowired
	RoleRepository roleRepository;

	@GetMapping({ "/", "/inicio" })
	public String index(Model model) {

		return "index";
	}

	@GetMapping({ "/login" })
	public String login() {
		return "index";
	}

	@GetMapping({ "/inicioa" })
	public String inicio(Model model) {
		model.addAttribute("userList", userService.getAllUsers());
		return "admin/admin";
	}

	@GetMapping({ "/inicioc" })
	public String cliente(Model model) {
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("burguerList", burguerservice.getAllBurguers());
		return "cliente/usuario";
	}

	@GetMapping("/registro")
	public String registro(Model model) {
		long id;
		id = 2;
		model.addAttribute("registro", new User());
		model.addAttribute("roles", roleRepository.findAll());

		return "registro";
	}
	
	@GetMapping("/registro/cancelar")
	public String cancelarRegistro(Model model) {
		
		return "redirect:/inicio";
	}


	@PostMapping("/registro")
	public String crearUser(@Valid @ModelAttribute("registro") User user, BindingResult result, ModelMap model) {
		long id;
		id = 1;

		if (result.hasErrors()) {
			model.addAttribute("registro", user);
			model.addAttribute("roles", roleRepository.findAll());

		} else {
			try {
				userService.createUser(user);
				model.addAttribute("registro", new User());
				model.addAttribute("roles", roleRepository.findAll());

			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("registro", user);
				model.addAttribute("roles", roleRepository.findAll());
			}
		}

		model.addAttribute("roles", roleRepository.findAll());

		return "redirect:/inicio";
	}

	@GetMapping({ "/perfil" })
	public String iniciop(Model model) {
		model.addAttribute("userList", userService.getAllUsers());
		return "admin/perfil";
	}

	@GetMapping({ "/perfilcliente" })
	public String inicioc(Model model) {
		model.addAttribute("userList", userService.getAllUsers());
		return "cliente/perfil-cliente";
	}

	

	@GetMapping("/editarUsuario/{id}")
	public String getEditarUsuario(Model model, @PathVariable(name = "id") Long id) throws Exception {

		User user = userService.getUserById(id);

		model.addAttribute("editarUsuario", user);
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("passwordForm", new ChangePasswordForm(id));

		return "admin/editarusuario";
	}

	@PostMapping("/editarUsuario")
	public String postEditarUsuario(@Valid @ModelAttribute("editarusuario") User user, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("editarusuario", user);
			model.addAttribute("roles", roleRepository.findAll());
		} else {
			try {
				userService.updateUser(user);
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("editarusuario", user);
				model.addAttribute("roles", roleRepository.findAll());
			}
		}

		model.addAttribute("userList", userService.getAllUsers());
		return "redirect:/inicioa";

	}

	@GetMapping("/editarUsuario/cancel")
	public String cancelEditarUsuario(ModelMap model) {
		return "redirect:/inicioa";
	}

	@GetMapping("/borrarUsuario/{id}")
	public String borrarUsuario(Model model, @PathVariable(name = "id") Long id) {
		try {
			userService.borrarUsuario(id);
		} catch (UsernameOrIdNotFound uoin) {
			model.addAttribute("listErrorMessage", uoin.getMessage());
		}
		return "redirect:/inicioa";
	}

	@GetMapping("/borrarUsuario/cancel")
	public String cancelborrarUsuario(Model model) {

		return "redirect:/inicioa";
	}

	@GetMapping("/borrarSugerencia/{id}")
	public String borrarSugerencia(Model model, @PathVariable(name = "id") Long id) {
		try {
			sugerenciasService.borrarSugerencia(id);
		} catch (Exception e) {
			model.addAttribute("listErrorMessage", e.getMessage());
		}
		return "redirect:/inicioa";
	}

	@GetMapping("/borrarSugerencia/cancel")
	public String cancelborrarSugerencia(Model model) {

		return "redirect:/contacto";
	}

	@PostMapping("/editUser/changePassword")
	public ResponseEntity postEditUseChangePassword(@Valid @RequestBody ChangePasswordForm form, Errors errors) {
		try {
			if (errors.hasErrors()) {
				String result = errors.getAllErrors().stream().map(x -> x.getDefaultMessage())
						.collect(Collectors.joining(""));

				throw new Exception(result);
			}
			userService.changePassword(form);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("Success");
	}
}