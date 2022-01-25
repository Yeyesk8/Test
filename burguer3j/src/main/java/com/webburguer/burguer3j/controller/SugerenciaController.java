
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
public class SugerenciaController {
	
	@Autowired
	SugerenciasService sugerenciasService;
	
	@GetMapping({ "/sugerencia" })
	public String Sugerencia(Model model) {
		model.addAttribute("sugerenciaList", sugerenciasService.getAllSugerencias());

		return "admin/sugerencias";
	}

	@PostMapping("/sugerencia")
	public String crearSugerencias(@Valid @ModelAttribute("sugerencia") Sugerencias sugerencias,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			model.addAttribute("sugerencia", sugerencias);

		} else {
			try {
				sugerenciasService.createSugerencia(sugerencias);
				model.addAttribute("sugerencia", new Sugerencias());

			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("sugerencia", sugerencias);
			}
		}
		return "redirect:/inicio";
	}
}