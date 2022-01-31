
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

import com.webburguer.burguer3j.Exception.BaguetteNameOrIdNotFound;
import com.webburguer.burguer3j.entity.Baguette;
import com.webburguer.burguer3j.entity.Burguer;
import com.webburguer.burguer3j.service.BaguetteService;
import com.webburguer.burguer3j.service.UploadFileService;

@Controller
public class BaguetteController {

	private final Logger LOGGER = LoggerFactory.getLogger(BaguetteController.class);

	@Autowired
	BaguetteService baguetteservice;

	@Autowired
	private UploadFileService upload;

	@GetMapping({ "/baguettes" })
	public String inicio(Model model) {
		model.addAttribute("nuevaBaguette", new Baguette());
		model.addAttribute("baguetteList", baguetteservice.getAllBaguettes());
		return "admin/baguette";
	}

	@GetMapping({ "/baguette" })
	public String inicioc(Model model) {
		model.addAttribute("baguetteList", baguetteservice.getAllBaguettes());
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
	public String crearBaguette(@Valid @ModelAttribute("nuevaBaguette") Baguette baguette, @RequestParam("img") MultipartFile file, BindingResult result, ModelMap model) throws IOException {

		LOGGER.info("Este es el objeto baguette {}", baguette);

		if (result.hasErrors()) {
			model.addAttribute("nuevaBaguette", baguette);

		} else {
			try {
				if (baguette.getId() == null) { // cuando se crea una baguette
					String nombreImagen = upload.saveImage(file);
					baguette.setImagen(nombreImagen);
				} else {

				}
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
	public String postEditarBaguette(@Valid @ModelAttribute("editarbaguette") Baguette baguette, @RequestParam("img") MultipartFile file, BindingResult result, ModelMap model) throws IOException {

		Baguette ba = new Baguette();
		ba = baguetteservice.get(baguette.getId()).get();

		if (result.hasErrors()) {
			model.addAttribute("editarbaguette", baguette);
		} else {
			
			if (file.isEmpty()) { // editamos la baguette pero no cambiamos la imagen

				baguette.setImagen(ba.getImagen());
			} else {// cuando se edita tambien la imagen
					// se elimina cuando no sea la imagen por defecto y se actualiza a la nueva
				if (!ba.getImagen().equals("defecto.jpg")) {
					upload.deleteImage(ba.getImagen());
				}
				String nombreImagen = upload.saveImage(file);
				baguette.setImagen(nombreImagen);
			}
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

	@GetMapping("/editarBaguette/cancelar")
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