package com.webburguer.burguer3j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import javax.validation.Valid;

import com.webburguer.burguer3j.service.UploadFileService;
import com.webburguer.burguer3j.Exception.BurguerNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Burguer;
import com.webburguer.burguer3j.service.BurguerService;

@Controller
public class BurguerController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(BurguerController.class);

	@Autowired
	BurguerService burguerservice;
	
	@Autowired
	private UploadFileService upload;
	
	@GetMapping({"/burguers"})
	public String inicio(Model model, Burguer burguer) {	
		
		model.addAttribute("nuevaBurguer", new Burguer());
		model.addAttribute("burguerList",burguerservice.getAllBurguers());
	
		return "admin/burguer";
	}
	
	
	@GetMapping({"/nuevaBurguer"})
	public String nuevaBurguer(Model model) {
		
		model.addAttribute("nuevaBurguer", new Burguer());
		return "/admin/aniadirburguer";
	}
	
	@GetMapping("/nuevaBurguer/cancelar")
	public String cancelarNuevaBurguer(Model model) {
		return "redirect:/burguers";
	}
	

	@PostMapping("/nuevaBurguer")
	public String crearBurguer(@Valid @ModelAttribute("nuevaBurguer") Burguer burguer, @RequestParam("img") MultipartFile file, BindingResult result, ModelMap model) throws IOException {
		
		LOGGER.info("Este es el objeto burguer {}",burguer);
		
		if (result.hasErrors()) {
			model.addAttribute("nuevaBurguer", burguer);
			
			
		} else {
			try {
				if (burguer.getId()==null) { // cuando se crea una burguer
					String nombreImagen= upload.saveImage(file);
					burguer.setImagen(nombreImagen);
				}else {
					
				}
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
		
		return "admin/editarburguer";
	}

	@PostMapping("/editarBurguer")
	public String postEditarBurguer(@Valid @ModelAttribute("editarburguer") Burguer burguer, @RequestParam("img") MultipartFile file, BindingResult result, ModelMap model) throws IOException {
		
		Burguer b = new Burguer();
		b=burguerservice.get(burguer.getId()).get();
		
		if (result.hasErrors()) {
			model.addAttribute("editarburguer", burguer);
					
		} else {

			if (file.isEmpty()) { // editamos la burguer pero no cambiamos la imagen
				
				burguer.setImagen(b.getImagen());
			}
			else {// cuando se edita tambien la imagen			
				//se elimina cuando no sea la imagen por defecto y se actualiza a la nueva
				if (!b.getImagen().equals("defecto.jpg")) {
					upload.deleteImage(b.getImagen());
				}
				String nombreImagen= upload.saveImage(file);
				burguer.setImagen(nombreImagen);
			}
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