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
public class Baguette implements Serializable {

	
	private static final long serialVersionUID = -5954257602223435011L;

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
	private String contenido;


	@NotBlank
	@Column
	private String tipo_queso;

	@NotBlank
	@Column
	private String lechuga;


	@NotBlank
	@Column
	private String tomate;

	@NotBlank
	@Column
	private String mahonesa;
	
	@NotBlank
	@Column
	private String descripcion;
	
	@NotBlank
	@Column
	private String imagen;
	
	@NotBlank
	@Column
	private double precio;
	
	public Baguette() {
		super();
	}

	public Baguette(Long id) {
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


	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getTipo_queso() {
		return tipo_queso;
	}

	public void setTipo_queso(String tipo_queso) {
		this.tipo_queso = tipo_queso;
	}

	public String getLechuga() {
		return lechuga;
	}

	public void setLechuga(String lechuga) {
		this.lechuga = lechuga;
	}

	public String getTomate() {
		return tomate;
	}

	public void setTomate(String tomate) {
		this.tomate = tomate;
	}

	public String getMahonesa() {
		return mahonesa;
	}

	public void setMahonesa(String mahonesa) {
		this.mahonesa = mahonesa;
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
		return "Baguette [id=" + id + ", name=" + name + ", contenido=" + contenido + ", tipo_queso=" + tipo_queso
				+ ", lechuga=" + lechuga + ", tomate=" + tomate + ", mahonesa=" + mahonesa + ", descripcion="
				+ descripcion + ", imagen=" + imagen + ", precio=" + precio + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(contenido, descripcion, id, imagen, lechuga, mahonesa, name, precio, tipo_queso, tomate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Baguette other = (Baguette) obj;
		return Objects.equals(contenido, other.contenido) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(id, other.id) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(lechuga, other.lechuga) && Objects.equals(mahonesa, other.mahonesa)
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(tipo_queso, other.tipo_queso) && Objects.equals(tomate, other.tomate);
	}

	
}
