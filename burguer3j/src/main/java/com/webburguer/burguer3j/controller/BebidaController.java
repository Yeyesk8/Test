


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

import com.webburguer.burguer3j.Exception.BebidaNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Bebida;
import com.webburguer.burguer3j.entity.Patata;
import com.webburguer.burguer3j.service.BebidaService;
import com.webburguer.burguer3j.service.UploadFileService;



@Controller
public class BebidaController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(BebidaController.class);

	@Autowired
	BebidaService bebidaservice;
	
	@Autowired
	private UploadFileService upload;
	
	@GetMapping({"/bebidas"})
	public String inicio(Model model) {	
		model.addAttribute("nuevaBebida", new Bebida());
		model.addAttribute("bebidaList",bebidaservice.getAllBebidas());
		return "admin/bebida";
	}
	
	@GetMapping({"/bebida"})
	public String inicioc(Model model) {	
		model.addAttribute("bebidaList",bebidaservice.getAllBebidas());
		return "cliente/bebida-cliente";
	}
	
	@GetMapping("/nuevaBebida")
	public String nuevaBebida(Model model) {
		
		model.addAttribute("nuevaBebida", new Bebida());
		return "/admin/aniadirBebida";
	}
	
	@GetMapping("/nuevaBebida/cancelar")
	public String cancelarnuevaBebida(Model model) {
		return "redirect:/bebidas";
	}
	
	@PostMapping("/nuevaBebida")
	public String crearBebida(@Valid @ModelAttribute("nuevaBebida") Bebida bebida, @RequestParam("img") MultipartFile file, BindingResult result, ModelMap model) throws IOException {
		
		if (result.hasErrors()) {
			model.addAttribute("nuevaBebida", bebida);
			
			
		} else {
			try {
				if (bebida.getId() == null) { // cuando se crea una bebida
					String nombreImagen = upload.saveImage(file);
					bebida.setImagen(nombreImagen);
				} else {

				}
				bebidaservice.createBebida(bebida);
				model.addAttribute("nuevaBebida", new Bebida());

			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("nuevaBebida", bebida);
			}
		}

		return "redirect:/bebidas";
	}
	
	@GetMapping("/editarBebida/{id}")
	public String geteditarBebida(Model model, @PathVariable(name = "id") Long id) throws Exception {
		
		Bebida bebida = bebidaservice.getBebidaById(id);
		model.addAttribute("editarBebida", bebida);
		
		return "admin/editarbebida";
	}

	@PostMapping("/editarBebida")
	public String posteditarBebida(@Valid @ModelAttribute("editarbebida") Bebida bebida, @RequestParam("img") MultipartFile file, BindingResult result, ModelMap model) throws IOException {
		
		Bebida be = new Bebida();
		be = bebidaservice.get(bebida.getId()).get();
		
		if (result.hasErrors()) {
			model.addAttribute("editarbebida", bebida);
			
		} else {
			if (file.isEmpty()) { // editamos la bebida pero no cambiamos la imagen

				bebida.setImagen(be.getImagen());
			} else {// cuando se edita tambien la imagen
					// se elimina cuando no sea la imagen por defecto y se actualiza a la nueva
				if (!be.getImagen().equals("defecto.jpg")) {
					upload.deleteImage(be.getImagen());
				}
				String nombreImagen = upload.saveImage(file);
				bebida.setImagen(nombreImagen);
			}
			try {
				bebidaservice.updateBebida(bebida);
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("editarbebida", bebida);
			}
		}

		model.addAttribute("bebidaList", bebidaservice.getAllBebidas());
		
		return "redirect:/bebidas";

	}
	
	@GetMapping("/editarBebida/cancelar")
	public String canceleditarBebida(ModelMap model) {
		
		return "redirect:/bebidas";
	}

	@GetMapping("/borrarBebida/{id}")
	public String borrarBebida(Model model, @PathVariable(name = "id") Long id) {
		try {
			bebidaservice.borrarBebida(id);
		} catch (BebidaNameOrIdNotFound uoin) {
			model.addAttribute("listErrorMessage", uoin.getMessage());
		}
		return "redirect:/bebidas";
	}
	
	@GetMapping("/borrarBebida/cancel")
	public String cancelborrarBebida(Model model) {

		return "redirect:/bebidas";
	}

	
	
}