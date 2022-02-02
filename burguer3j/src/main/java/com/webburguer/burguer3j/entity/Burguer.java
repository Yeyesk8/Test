
package com.webburguer.burguer3j.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "burguer")
public class Burguer implements Serializable {

	private static final long serialVersionUID = 6902047601899992555L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@Column
	@NotBlank
	@Size(min = 5, max = 50, message = "No se cumple las reglas del tamaño")
	private String name;

	@Column
	@NotBlank
	private String tipo_carne;
	

	@Column
	@NotBlank
	private String gluten;

	@NotBlank
	@Column
	private String tipo_queso;

	@NotBlank
	@Column
	private String lechuga;

	@NotBlank
	@Column
	private String cebolla;

	@NotBlank
	@Column
	private String bacon;

	@NotBlank
	@Column
	private String tipo_huevo;

	@NotBlank
	@Column
	private String pepinillo;

	@NotBlank
	@Column
	private String tomate;

	@NotBlank
	@Column
	private String york;
	
	@NotBlank
	@Column
	private String descripcion;
	
	@NotBlank
	@Column
	private String imagen;
	
	@NotBlank
	@Column
	private double precio;
	
	@NotBlank
	@Column
	private int cantidad;
	
	@ManyToOne
	private User usuario;
	
	

	
	public Burguer() {
		super();
	}

	public Burguer(Long id) {
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

	public String getTipo_carne() {
		return tipo_carne;
	}

	public void setTipo_carne(String tipo_carne) {
		this.tipo_carne = tipo_carne;
	}

	public String getGluten() {
		return gluten;
	}

	public void setGluten(String gluten) {
		this.gluten = gluten;
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

	public String getCebolla() {
		return cebolla;
	}

	public void setCebolla(String cebolla) {
		this.cebolla = cebolla;
	}

	public String getBacon() {
		return bacon;
	}

	public void setBacon(String bacon) {
		this.bacon = bacon;
	}

	public String getTipo_huevo() {
		return tipo_huevo;
	}

	public void setTipo_huevo(String tipo_huevo) {
		this.tipo_huevo = tipo_huevo;
	}

	public String getPepinillo() {
		return pepinillo;
	}

	public void setPepinillo(String pepinillo) {
		this.pepinillo = pepinillo;
	}

	public String getTomate() {
		return tomate;
	}

	public void setTomate(String tomate) {
		this.tomate = tomate;
	}

	public String getYork() {
		return york;
	}

	public void setYork(String york) {
		this.york = york;
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
	

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Burguer [id=" + id + ", name=" + name + ", tipo_carne=" + tipo_carne + ", gluten=" + gluten
				+ ", tipo_queso=" + tipo_queso + ", lechuga=" + lechuga + ", cebolla=" + cebolla + ", bacon=" + bacon
				+ ", tipo_huevo=" + tipo_huevo + ", pepinillo=" + pepinillo + ", tomate=" + tomate + ", york=" + york
				+ ", descripcion=" + descripcion + ", imagen=" + imagen + ", precio=" + precio + ", cantidad="
				+ cantidad + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bacon, cantidad, cebolla, descripcion, gluten, id, imagen, lechuga, name, pepinillo, precio,
				tipo_carne, tipo_huevo, tipo_queso, tomate, york);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Burguer other = (Burguer) obj;
		return Objects.equals(bacon, other.bacon) && cantidad == other.cantidad
				&& Objects.equals(cebolla, other.cebolla) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(gluten, other.gluten) && Objects.equals(id, other.id)
				&& Objects.equals(imagen, other.imagen) && Objects.equals(lechuga, other.lechuga)
				&& Objects.equals(name, other.name) && Objects.equals(pepinillo, other.pepinillo)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(tipo_carne, other.tipo_carne) && Objects.equals(tipo_huevo, other.tipo_huevo)
				&& Objects.equals(tipo_queso, other.tipo_queso) && Objects.equals(tomate, other.tomate)
				&& Objects.equals(york, other.york);
	}
	
	

	

	
}
