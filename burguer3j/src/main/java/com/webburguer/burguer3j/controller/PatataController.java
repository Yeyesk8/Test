
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

import com.webburguer.burguer3j.Exception.PatataNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Baguette;
import com.webburguer.burguer3j.entity.Patata;
import com.webburguer.burguer3j.service.PatataService;
import com.webburguer.burguer3j.service.UploadFileService;

@Controller
public class PatataController {

	private final Logger LOGGER = LoggerFactory.getLogger(PatataController.class);

	@Autowired
	PatataService patataservice;

	@Autowired
	private UploadFileService upload;

	@GetMapping({ "/patatas" })
	public String inicio(Model model) {
		model.addAttribute("nuevaPatata", new Patata());
		model.addAttribute("patataList", patataservice.getAllPatatas());
		return "admin/patata";
	}

	@GetMapping({ "/patata" })
	public String inicioc(Model model) {
		model.addAttribute("patataList", patataservice.getAllPatatas());
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
	public String crearPatata(@Valid @ModelAttribute("nuevaPatata") Patata patata, @RequestParam("img") MultipartFile file, BindingResult result, ModelMap model) throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("nuevaPatata", patata);

		} else {
			try {
				if (patata.getId() == null) { // cuando se crea unas patatas
					String nombreImagen = upload.saveImage(file);
					patata.setImagen(nombreImagen);
				} else {

				}
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
	public String postEditarPatata(@Valid @ModelAttribute("editarpatata") Patata patata, @RequestParam("img") MultipartFile file, BindingResult result, ModelMap model) throws IOException {
		
		Patata p = new Patata();
		p = patataservice.get(patata.getId()).get();

		if (result.hasErrors()) {
			model.addAttribute("editarpatata", patata);
			
		} else {
			if (file.isEmpty()) { // editamos las patatas pero no cambiamos la imagen

				patata.setImagen(p.getImagen());
			} else {// cuando se edita tambien la imagen
					// se elimina cuando no sea la imagen por defecto y se actualiza a la nueva
				if (!p.getImagen().equals("defecto.jpg")) {
					upload.deleteImage(p.getImagen());
				}
				String nombreImagen = upload.saveImage(file);
				patata.setImagen(nombreImagen);
			}
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