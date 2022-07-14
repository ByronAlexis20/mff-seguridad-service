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
@Table(name = "seg_opcion")
public class Opcion implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_opcion", columnDefinition = "int4")
	private Integer idOpcion;

	@Column(name = "id_opcion_padre", columnDefinition = "int4")
	private Integer idOpcionPadre;

	@Size(max = 150)
	@Column(name = "descripcion", length = 150)
	private String descripcion;

	@Size(max = 150)
	@Column(name = "vista", length = 150)
	private String vista;

	@Size(max = 100)
	@Column(name = "icono", length = 100)
	private String icono;

	@Column(name = "posicion", columnDefinition = "int4")
	private Integer posicion;

	@Size(max = 100)
	@Column(name = "etiqueta", length = 100)
	private String etiqueta;

	@Size(max = 1)
	@Column(name = "estado", length = 1)
	private String estado;

	public Opcion() {
		super();
	}

	public Opcion(Integer idOpcion, Integer idOpcionPadre, String descripcion, String vista, String icono,
			Integer posicion, String etiqueta, String estado) {
		super();
		this.idOpcion = idOpcion;
		this.idOpcionPadre = idOpcionPadre;
		this.descripcion = descripcion;
		this.vista = vista;
		this.icono = icono;
		this.posicion = posicion;
		this.etiqueta = etiqueta;
		this.estado = estado;
	}

	public Integer getIdOpcion() {
		return idOpcion;
	}

	public void setIdOpcion(Integer idOpcion) {
		this.idOpcion = idOpcion;
	}

	public Integer getIdOpcionPadre() {
		return idOpcionPadre;
	}

	public void setIdOpcionPadre(Integer idOpcionPadre) {
		this.idOpcionPadre = idOpcionPadre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getVista() {
		return vista;
	}

	public void setVista(String vista) {
		this.vista = vista;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
