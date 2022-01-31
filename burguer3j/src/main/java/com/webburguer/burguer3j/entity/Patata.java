package com.webburguer.burguer3j.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Patata implements Serializable {

	
	
	private static final long serialVersionUID = -1043414434479761132L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@Column
	@NotBlank
	@Size(min = 5, max = 8, message = "No se cumple las reglas del tamaño")
	private String name;
	
	@Column
	@NotBlank
	private String gluten;


	@Column
	@NotBlank
	private String tamanio_plato;
	
	@NotBlank
	@Column
	private String descripcion;
	
	@NotBlank
	@Column
	private String imagen;
	
	@NotBlank
	@Column
	private double precio;


	
	public Patata() {
		super();
	}

	public Patata(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGluten() {
		return gluten;
	}

	public void setGluten(String gluten) {
		this.gluten = gluten;
	}

	public String getTamanio_plato() {
		return tamanio_plato;
	}

	public void setTamanio_plato(String tamanio_plato) {
		this.tamanio_plato = tamanio_plato;
	}
	
	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Patata [id=" + id + ", name=" + name + ", gluten=" + gluten + ", tamanio_plato=" + tamanio_plato
				+ ", descripcion=" + descripcion + ", imagen=" + imagen + ", precio=" + precio + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, gluten, id, imagen, name, precio, tamanio_plato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patata other = (Patata) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(gluten, other.gluten)
				&& Objects.equals(id, other.id) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(tamanio_plato, other.tamanio_plato);
	}

	
}