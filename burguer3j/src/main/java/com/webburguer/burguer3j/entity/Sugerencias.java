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
public class Sugerencias implements Serializable {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7528261423593774386L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@Column
	@NotBlank
	private String fullname;
	
	@Column
	@NotBlank
	private String email;


	@Column
	@NotBlank
	private String mensaje;


	
	
	public Sugerencias() {
		super();
	}
	
	public Sugerencias(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Sugerencias [id=" + id + ", fullname=" + fullname + ", email=" + email + ", mensaje=" + mensaje + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, fullname, id, mensaje);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sugerencias other = (Sugerencias) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullname, other.fullname)
				&& Objects.equals(id, other.id) && Objects.equals(mensaje, other.mensaje);
	}




	
	
	
}