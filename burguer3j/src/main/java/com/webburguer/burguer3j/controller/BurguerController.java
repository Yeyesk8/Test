package com.webburguer.burguer3j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




import javax.validation.Valid;

import com.webburguer.burguer3j.Exception.BurguerNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Burguer;
import com.webburguer.burguer3j.service.BurguerService;

@Controller
public class BurguerController {

	@Autowired
	BurguerService burguerservice;
	
	@GetMapping({"/burguers"})
	public String inicio(Model model, Burguer burguer) {	
		
		model.addAttribute("nuevaBurguer", new Burguer());
		model.addAttribute("burguerList",burguerservice.getAllBurguers());
		model.addAttribute("editarBurguer", burguer);
		
		return "admin/burguer";
	}
	
	
	@GetMapping({"/nuevaBurguer"})
	public String nuevaBurguer(Model model) {
		
		model.addAttribute("nuevaBurguer", new Burguer());
		return "redirect:/burguers";
	}
	
	@GetMapping("/nuevaBurguer/cancelar")
	public String cancelarNuevaBurguer(Model model) {
		return "redirect:/burguers";
	}
	

	@PostMapping("/nuevaBurguer")
	public String crearBurguer(@Valid @ModelAttribute("nuevaBurguer") Burguer burguer, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.addAttribute("nuevaBurguer", burguer);
			
			
		} else {
			try {
				burguerservice.createBurguer(burguer);
				model.addAttribute("nuevaBurguer", new Burguer());

			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("nuevaBurguer", burguer);
			}
		}

		return "redirect:/burguers";
	}
	

	@GetMapping("/editarBurguer/{id}")
	public String getEditarBurguer(Model model, @PathVariable(name = "id") Long id) throws Exception {
		
		Burguer burguer = burguerservice.getBurguerById(id);
		model.addAttribute("editarBurguer", burguer);
		
		return "redirect:/burguers";
	}

	@PostMapping("/editarBurguer")
	public String postEditarBurguer(@Valid @ModelAttribute("editarburguer") Burguer burguer, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.addAttribute("editarburguer", burguer);
			
		} else {
			try {
				burguerservice.updateBurguer(burguer);
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("editarburguer", burguer);
			}
		}

		model.addAttribute("burguerList", burguerservice.getAllBurguers());
		
		return "redirect:/burguers";

	}

	@GetMapping("/editarBurguer/cancelar")
	public String cancelEditarBurguer(ModelMap model) {
		
		return "redirect:/burguers";
	}

	@GetMapping("/borrarBurguer/{id}")
	public String borrarBurguer(Model model, @PathVariable(name = "id") Long id) {
		try {
			burguerservice.borrarBurguer(id);
		} catch (BurguerNameOrIdNotFound uoin) {
			model.addAttribute("listErrorMessage", uoin.getMessage());
		}
		return "redirect:/burguers";
	}
	
	@GetMapping("/borrarBurguer/cancel")
	public String cancelborrarBurguer(Model model) {

		return "redirect:/burguers";
	}


	
}