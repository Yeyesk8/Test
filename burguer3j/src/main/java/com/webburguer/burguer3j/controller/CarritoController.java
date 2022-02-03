package com.webburguer.burguer3j.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webburguer.burguer3j.entity.Burguer;
import com.webburguer.burguer3j.entity.DetalleOrden;
import com.webburguer.burguer3j.entity.Orden;
import com.webburguer.burguer3j.entity.User;
import com.webburguer.burguer3j.service.BurguerService;
import com.webburguer.burguer3j.service.DetalleOrdenService;
import com.webburguer.burguer3j.service.OrdenService;
import com.webburguer.burguer3j.service.UserService;

@Controller
@RequestMapping("/")
public class CarritoController {

	private final Logger log = LoggerFactory.getLogger(CarritoController.class);

	@Autowired
	private BurguerService burguerService;
	
	@Autowired
	private UserService usuarioService;
	
	
	@Autowired
	private OrdenService ordenService;
	
	@Autowired
	private DetalleOrdenService detalleOrdenService;

	// para almacenar los detalles de la orden
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	// datos de la orden
	Orden orden = new Orden();
	

	@GetMapping("burguerInfo/{id}")
	public String burguerInfo(@PathVariable Long id, Model model) {
		log.info("Id Burguer enviada como parámetro {}", id);
		Burguer burguer = new Burguer();
		Optional<Burguer> burguerOptional = burguerService.get(id);
		burguer = burguerOptional.get();

		model.addAttribute("burguer", burguer);

		return "cliente/burguerHome";
	}

	@PostMapping("/carrito")
	public String addCart(@RequestParam Long id, @RequestParam Integer cantidad, Model model) {
		DetalleOrden detalleOrden = new DetalleOrden();
		Burguer burguer = new Burguer();
		double sumaTotal = 0;

		Optional<Burguer> optionalBurguer = burguerService.get(id);
		log.info("Burguer añadida: {}", optionalBurguer.get());
		log.info("Cantidad: {}", cantidad);
		burguer = optionalBurguer.get();

		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(burguer.getPrecio());
		detalleOrden.setNombre(burguer.getName());
		detalleOrden.setTotal(burguer.getPrecio() * cantidad);
		detalleOrden.setBurguer(burguer);
		
		//validar que le producto no se añada 2 veces
		Long idProducto=burguer.getId();
		boolean ingresado=detalles.stream().anyMatch(b -> b.getId()==idProducto);
		
		if (!ingresado) {
			detalles.add(detalleOrden);
		}
		
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}

	// quitar un producto del carrito
	@GetMapping("/eliminar/carrito/{id}")
	public String deleteProductoCart(@PathVariable Long id, Model model) {

		// lista nueva de prodcutos
		List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

		for (DetalleOrden detalleOrden : detalles) {
			if (detalleOrden.getBurguer().getId() != id) {
				ordenesNueva.add(detalleOrden);
			}
		}

		// poner la nueva lista con los productos restantes
		detalles = ordenesNueva;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "cliente/carrito";
	}
	
	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		//sesion
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "/cliente/carrito";
	}
	
	@GetMapping("/order")
	public String order(Model model, HttpSession session) {
		
		User usuario =usuarioService.findById( Long.parseLong(session.getAttribute("idusuario").toString())).get();
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);
		
		return "cliente/resumenorden";
	}
	
	// guardar la orden
	@GetMapping("/GuardarOrden")
	public String GuardarOrden(HttpSession session ) {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenService.generarNumeroOrden());
		
		//usuario
		User usuario =usuarioService.findById( Long.parseLong(session.getAttribute("idusuario").toString())  ).get();
		
		orden.setUsuario(usuario);
		ordenService.createOrden(orden);
		
		//guardar detalles
		for (DetalleOrden dt:detalles) {
			dt.setOrden(orden);
			detalleOrdenService.createDetalleOrden(dt);
		}
		
		///limpiar lista y orden
		orden = new Orden();
		detalles.clear();
		
		return "redirect:/";
	}
	
}
