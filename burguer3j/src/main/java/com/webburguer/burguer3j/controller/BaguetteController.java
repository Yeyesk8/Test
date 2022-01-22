
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

import com.webburguer.burguer3j.Exception.BaguetteNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Baguette;
import com.webburguer.burguer3j.service.BaguetteService;


@Controller
public class BaguetteController {

	@Autowired
	BaguetteService baguetteservice;
	
	@GetMapping({"/baguettes"})
	public String inicio(Model model) {	
		model.addAttribute("baguetteList",baguetteservice.getAllBaguettes());
		return "admin/baguette";
	}
	
	@GetMapping({"/baguette"})
	public String inicioc(Model model) {	
		model.addAttribute("baguetteList",baguetteservice.getAllBaguettes());
		return "cliente/baguette-cliente";
	}
	

	@GetMapping("/nuevaBaguette")
	public String nuevaBaguette(Model model) {
		
		model.addAttribute("nuevaBaguette", new Baguette());
		return "/admin/aniadirbaguette";
	}
	
	@GetMapping("/nuevaBaguette/cancelar")
	public String cancelarnuevaBaguette(Model model) {
		return "redirect:/baguettes";
	}
	

	@PostMapping("/nuevaBaguette")
	public String crearBaguette(@Valid @ModelAttribute("nuevaBaguette") Baguette baguette, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.addAttribute("nuevaBaguette", baguette);
			
			
		} else {
			try {
				baguetteservice.createBaguette(baguette);
				model.addAttribute("nuevaBaguette", new Baguette());

			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("nuevaBaguette", baguette);
			}
		}

		return "redirect:/baguettes";
	}
	
	@GetMapping("/editarBaguette/{id}")
	public String getEditarBaguette(Model model, @PathVariable(name = "id") Long id) throws Exception {
		
		Baguette baguette = baguetteservice.getBaguetteById(id);
		model.addAttribute("editarBaguette", baguette);
		
		return "admin/editarbaguette";
	}

	@PostMapping("/editarBaguette")
	public String postEditarBaguette(@Valid @ModelAttribute("editarbaguette") Baguette baguette, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.addAttribute("editarbaguette", baguette);
		} else {
			try {
				baguetteservice.updateBaguette(baguette);
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("editarbaguette", baguette);
			}
		}

		model.addAttribute("baguetteList", baguetteservice.getAllBaguettes());
		
		return "redirect:/baguettes";

	}

	@GetMapping("/editarBaguette/cancel")
	public String cancelEditarBaguette(ModelMap model) {
		
		return "redirect:/baguettes";
	}

	@GetMapping("/borrarBaguette/{id}")
	public String borrarBaguette(Model model, @PathVariable(name = "id") Long id) {
		try {
			baguetteservice.borrarBaguette(id);
		} catch (BaguetteNameOrIdNotFound uoin) {
			model.addAttribute("listErrorMessage", uoin.getMessage());
		}
		return "redirect:/baguettes";
	}
	
	@GetMapping("/borrarBaguette/cancel")
	public String cancelborrarBaguette(Model model) {

		return "redirect:/baguettes";
	}


	
}