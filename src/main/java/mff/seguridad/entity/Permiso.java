package mff.seguridad.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "seg_permiso")
public class Permiso implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permiso", columnDefinition = "int4")
	private Integer idPermiso;

	@ManyToOne
	@JoinColumn(name = "id_opcion")
	private Opcion opcion;

	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;

	@Size(max = 1)
	@Column(name = "estado", length = 1)
	private String estado;

	public Permiso() {
		super();
	}

	public Permiso(Integer idPermiso, Opcion opcion, Perfil perfil, @Size(max = 1) String estado) {
		super();
		this.idPermiso = idPermiso;
		this.opcion = opcion;
		this.perfil = perfil;
		this.estado = estado;
	}

	public Integer getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(Integer idPermiso) {
		this.idPermiso = idPermiso;
	}

	public Opcion getOpcion() {
		return opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
