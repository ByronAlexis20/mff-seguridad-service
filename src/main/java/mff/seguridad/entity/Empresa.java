package mff.seguridad.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "seg_empresa")
public class Empresa implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa", columnDefinition = "int4")
	private Integer idEmpresa;

	@Size(max = 13)
	@Column(name = "ruc", length = 13)
	private String ruc;

	@Lob
	@Column(name = "logo")
	private byte[] logo;

	@Size(max = 100)
	@Column(name = "nombre", length = 100)
	private String nombre;

	@Size(max = 250)
	@Column(name = "slogan", length = 250)
	private String slogan;

	@Size(max = 1)
	@Column(name = "estado", length = 1)
	private String estado;

	public Empresa() {
		super();
	}

	public Empresa(Integer idEmpresa, @Size(max = 13) String ruc, byte[] logo, @Size(max = 100) String nombre,
			@Size(max = 250) String slogan, @Size(max = 1) String estado) {
		super();
		this.idEmpresa = idEmpresa;
		this.ruc = ruc;
		this.logo = logo;
		this.nombre = nombre;
		this.slogan = slogan;
		this.estado = estado;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
