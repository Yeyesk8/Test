
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
public class Bebida implements Serializable {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2549326702653745111L;

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
	private String alcohol;


	@NotBlank
	@Column
	private String tipo_vaso;
	
	@NotBlank
	@Column
	private String descripcion;
	
	@NotBlank
	@Column
	private String imagen;
	
	@NotBlank
	@Column
	private double precio;


	
	
	public Bebida() {
		super();
	}

	public Bebida(Long id) {
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

	public String getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}

	public String getTipo_vaso() {
		return tipo_vaso;
	}

	public void setTipo_vaso(String tipo_vaso) {
		this.tipo_vaso = tipo_vaso;
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
		return "Bebida [id=" + id + ", name=" + name + ", alcohol=" + alcohol + ", tipo_vaso=" + tipo_vaso
				+ ", descripcion=" + descripcion + ", imagen=" + imagen + ", precio=" + precio + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(alcohol, descripcion, id, imagen, name, precio, tipo_vaso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bebida other = (Bebida) obj;
		return Objects.equals(alcohol, other.alcohol) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(id, other.id) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(tipo_vaso, other.tipo_vaso);
	}

	
}
