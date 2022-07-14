package mff.seguridad.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "seg_perfil")
public class Perfil implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil", columnDefinition = "int4")
	private Integer idPerfil;

	@Size(max = 100)
	@Column(name = "nombre", length = 100)
	private String nombre;

	@Size(max = 250)
	@Column(name = "descripcion", length = 250)
	private String descripcion;

	@Size(max = 1)
	@Column(name = "estado", length = 1)
	private String estado;

	public Perfil() {
		super();
	}

	public Perfil(Integer idPerfil, @Size(max = 100) String nombre, @Size(max = 250) String descripcion,
			@Size(max = 1) String estado) {
		super();
		this.idPerfil = idPerfil;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
