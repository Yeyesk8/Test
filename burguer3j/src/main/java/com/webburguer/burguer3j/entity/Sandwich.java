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
public class Sandwich implements Serializable {

	
	private static final long serialVersionUID = 6309462155354864074L;

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
	private String bacon;


	@NotBlank
	@Column
	private String lechuga;

	@NotBlank
	@Column
	private String tomate;


	@NotBlank
	@Column
	private String esparragos;

	@NotBlank
	@Column
	private String tipo_huevo;
	
	@NotBlank
	@Column
	private String tipo_queso;
	
	@NotBlank
	@Column
	private String york;
	
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
	
	public Sandwich() {
		super();
	}

	public Sandwich(Long id) {
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

	public String getBacon() {
		return bacon;
	}

	public void setBacon(String bacon) {
		this.bacon = bacon;
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

	public String getEsparragos() {
		return esparragos;
	}

	public void setEsparragos(String esparragos) {
		this.esparragos = esparragos;
	}

	public String getTipo_huevo() {
		return tipo_huevo;
	}

	public void setTipo_huevo(String tipo_huevo) {
		this.tipo_huevo = tipo_huevo;
	}

	public String getTipo_queso() {
		return tipo_queso;
	}

	public void setTipo_queso(String tipo_queso) {
		this.tipo_queso = tipo_queso;
	}

	public String getYork() {
		return york;
	}

	public void setYork(String york) {
		this.york = york;
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
		return "Sandwich [id=" + id + ", name=" + name + ", gluten=" + gluten + ", bacon=" + bacon + ", lechuga="
				+ lechuga + ", tomate=" + tomate + ", esparragos=" + esparragos + ", tipo_huevo=" + tipo_huevo
				+ ", tipo_queso=" + tipo_queso + ", york=" + york + ", mahonesa=" + mahonesa + ", descripcion="
				+ descripcion + ", imagen=" + imagen + ", precio=" + precio + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bacon, descripcion, esparragos, gluten, id, imagen, lechuga, mahonesa, name, precio,
				tipo_huevo, tipo_queso, tomate, york);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sandwich other = (Sandwich) obj;
		return Objects.equals(bacon, other.bacon) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(esparragos, other.esparragos) && Objects.equals(gluten, other.gluten)
				&& Objects.equals(id, other.id) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(lechuga, other.lechuga) && Objects.equals(mahonesa, other.mahonesa)
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(tipo_huevo, other.tipo_huevo) && Objects.equals(tipo_queso, other.tipo_queso)
				&& Objects.equals(tomate, other.tomate) && Objects.equals(york, other.york);
	}

	
	
	
}