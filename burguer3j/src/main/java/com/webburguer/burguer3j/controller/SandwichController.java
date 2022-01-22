
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

import com.webburguer.burguer3j.Exception.SandwichNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Burguer;
import com.webburguer.burguer3j.entity.Sandwich;
import com.webburguer.burguer3j.service.SandwichService;

@Controller
public class SandwichController {

	@Autowired
	SandwichService sandwichservice;
	
	@GetMapping({"/sandwichs"})
	public String inicio(Model model, Sandwich sandwich) {
		model.addAttribute("nuevoSandwich", new Sandwich());
		model.addAttribute("sandwichList",sandwichservice.getAllSandwiches());
		model.addAttribute("editarSandwich", sandwich);
		return "admin/sandwich";
	}
	
	@GetMapping({"/sandwich"})
	public String inicioc(Model model) {	
		model.addAttribute("sandwichList",sandwichservice.getAllSandwiches());
		return "cliente/sandwich-cliente";
	}
	
	@GetMapping("/nuevoSandwich")
	public String nuevoSandwich(Model model) {
		
		model.addAttribute("nuevoSandwich", new Sandwich());
		return "/admin/aniadirsandwich";
	}
	
	@GetMapping("/nuevoSandwich/cancelar")
	public String cancelarnuevoSandwich(Model model) {
		return "redirect:/sandwichs";
	}
	

	@PostMapping("/nuevoSandwich")
	public String crearSandwich(@Valid @ModelAttribute("nuevoSandwich") Sandwich sandwich, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.addAttribute("nuevoSandwich", sandwich);
			
			
		} else {
			try {
				sandwichservice.createSandwich(sandwich);
				model.addAttribute("nuevoSandwich", new Sandwich());

			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("nuevoSandwich", sandwich);
			}
		}

		return "redirect:/sandwichs";
	}
	
	@GetMapping("/editarSandwich/{id}")
	public String getEditarSandwich(Model model, @PathVariable(name = "id") Long id) throws Exception {
		
		Sandwich sandwich = sandwichservice.getSandwichById(id);
		model.addAttribute("editarSandwich", sandwich);
		
		return "admin/editarsandwich";
	}

	@PostMapping("/editarSandwich")
	public String postEditarSandwich(@Valid @ModelAttribute("editarsandwich") Sandwich sandwich, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.addAttribute("editarsandwich", sandwich);
		} else {
			try {
				sandwichservice.updateSandwich(sandwich);
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("editarsandwich", sandwich);
			}
		}

		model.addAttribute("sandwichList", sandwichservice.getAllSandwiches());
		
		return "redirect:/sandwichs";

	}

	@GetMapping("/editarSandwich/cancel")
	public String cancelEditarSandwich(ModelMap model) {
		
		return "redirect:/sandwichs";
	}

	@GetMapping("/borrarSandwich/{id}")
	public String borrarSandwich(Model model, @PathVariable(name = "id") Long id) {
		try {
			sandwichservice.borrarSandwich(id);
		} catch (SandwichNameOrIdNotFound uoin) {
			model.addAttribute("listErrorMessage", uoin.getMessage());
		}
		return "redirect:/sandwichs";
	}
	
	@GetMapping("/borrarSandwich/cancel")
	public String cancelborrarSandwich(Model model) {

		return "redirect:/sandwichs";
	}


	
	
}