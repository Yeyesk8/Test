

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

import com.webburguer.burguer3j.Exception.PatataNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Burguer;
import com.webburguer.burguer3j.entity.Patata;
import com.webburguer.burguer3j.service.PatataService;


@Controller
public class PatataController {

	@Autowired
	PatataService patataservice;
	
	@GetMapping({"/patatas"})
	public String inicio(Model model) {	
		model.addAttribute("nuevaPatata", new Patata());
		model.addAttribute("patataList",patataservice.getAllPatatas());
		return "admin/patata";
	}
	
	@GetMapping({"/patata"})
	public String inicioc(Model model) {	
		model.addAttribute("patataList",patataservice.getAllPatatas());
		return "cliente/patata-cliente";
	}
	

	@GetMapping("/nuevaPatata")
	public String nuevaPatata(Model model) {
		
		model.addAttribute("nuevaPatata", new Patata());
		return "/admin/aniadirpatata";
	}
	
	@GetMapping("/nuevaPatata/cancelar")
	public String cancelarnuevaPatata(Model model) {
		
		return "redirect:/patatas";
	}
	

	@PostMapping("/nuevaPatata")
	public String crearPatata(@Valid @ModelAttribute("nuevaPatata") Patata patata, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.addAttribute("nuevaPatata", patata);
			
			
		} else {
			try {
				patataservice.createPatata(patata);
				model.addAttribute("nuevaPatata", new Patata());

			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("nuevaPatata", patata);
			}
		}

		return "redirect:/patatas";
	}
	
	@GetMapping("/editarPatata/{id}")
	public String getEditarPatata(Model model, @PathVariable(name = "id") Long id) throws Exception {
		
		Patata patata = patataservice.getPatataById(id);
		model.addAttribute("editarPatata", patata);
		
		return "admin/editarpatata";
	}

	@PostMapping("/editarPatata")
	public String postEditarPatata(@Valid @ModelAttribute("editarpatata") Patata patata, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.addAttribute("editarpatata", patata);
		} else {
			try {
				patataservice.updatePatata(patata);
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("editarpatata", patata);
			}
		}

		model.addAttribute("patataList", patataservice.getAllPatatas());
		
		return "redirect:/patatas";

	}

	@GetMapping("/editarPatata/cancelar")
	public String cancelEditarPatata(ModelMap model) {
		
		return "redirect:/patatas";
	}

	@GetMapping("/borrarPatata/{id}")
	public String borrarPatata(Model model, @PathVariable(name = "id") Long id) {
		try {
			patataservice.borrarPatata(id);
		} catch (PatataNameOrIdNotFound uoin) {
			model.addAttribute("listErrorMessage", uoin.getMessage());
		}
		return "redirect:/patatas";
	}
	
	@GetMapping("/borrarPatata/cancel")
	public String cancelborrarPatata(Model model) {

		return "redirect:/patatas";
	}


}