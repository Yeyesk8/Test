
package com.webburguer.burguer3j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.webburguer.burguer3j.Exception.SandwichNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Sandwich;
import com.webburguer.burguer3j.service.SandwichService;
import com.webburguer.burguer3j.service.UploadFileService;

@Controller
public class SandwichController {

	private final Logger LOGGER = LoggerFactory.getLogger(SandwichController.class);

	@Autowired
	SandwichService sandwichservice;

	@Autowired
	private UploadFileService upload;

	@GetMapping({ "/sandwichs" })
	public String inicio(Model model, Sandwich sandwich) {
		model.addAttribute("nuevoSandwich", new Sandwich());
		model.addAttribute("sandwichList", sandwichservice.getAllSandwiches());
		return "admin/sandwich";
	}

	@GetMapping({ "/sandwich" })
	public String inicioc(Model model) {
		model.addAttribute("sandwichList", sandwichservice.getAllSandwiches());
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
	public String crearSandwich(@Valid @ModelAttribute("nuevoSandwich") Sandwich sandwich, @RequestParam("img") MultipartFile file, BindingResult result, ModelMap model) throws IOException {

		LOGGER.info("Este es el objeto sandwich {}", sandwich);

		if (result.hasErrors()) {
			model.addAttribute("nuevoSandwich", sandwich);

		} else {
			try {
				if (sandwich.getId() == null) { // cuando se crea un sandwich
					String nombreImagen = upload.saveImage(file);
					sandwich.setImagen(nombreImagen);
				} else {

				}
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
	public String postEditarSandwich(@Valid @ModelAttribute("editarsandwich") Sandwich sandwich,@RequestParam("img") MultipartFile file, BindingResult result, ModelMap model) throws IOException {

		Sandwich s = new Sandwich();
		s = sandwichservice.get(sandwich.getId()).get();

		if (result.hasErrors()) {
			model.addAttribute("editarsandwich", sandwich);
			
		} else {
			if (file.isEmpty()) { // editamos el sandwich pero no cambiamos la imagen

				sandwich.setImagen(s.getImagen());
				
			} else {// cuando se edita tambien la imagen
					// se elimina cuando no sea la imagen por defecto y se actualiza a la nueva
				if (!s.getImagen().equals("defecto.jpg")) {
					upload.deleteImage(s.getImagen());
				}
				String nombreImagen = upload.saveImage(file);
				sandwich.setImagen(nombreImagen);
			}
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

	@GetMapping("/editarSandwich/cancelar")
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