package mff.seguridad.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "seg_usuario")
public class Usuario implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", columnDefinition = "int4")
	private Integer idUsuario;

	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;

	@Size(max = 10)
	@Column(name = "cedula", length = 10)
	private String cedula;

	@Size(max = 100)
	@Column(name = "nombres", length = 100)
	private String nombres;

	@Size(max = 100)
	@Column(name = "apellidos", length = 100)
	private String apellidos;

	@Size(max = 250)
	@Column(name = "direccion", length = 250)
	private String direccion;

	@Size(max = 10)
	@Column(name = "telefono", length = 10)
	private String telefono;

	@Size(max = 150)
	@Column(name = "usuario", length = 150)
	private String usuario;

	@Size(max = 150)
	@Column(name = "clave", length = 150)
	private String clave;

	@Lob
	@Column(name = "foto")
	private byte[] foto;

	@Size(max = 1)
	@Column(name = "estado", length = 1)
	private String estado;

	public Usuario() {
		super();
	}

	public Usuario(Integer idUsuario, Perfil perfil, @Size(max = 10) String cedula, @Size(max = 100) String nombre,
			@Size(max = 100) String apellido, @Size(max = 250) String direccion, @Size(max = 10) String telefono,
			@Size(max = 150) String usuario, @Size(max = 150) String clave, byte[] foto, @Size(max = 1) String estado) {
		super();
		this.idUsuario = idUsuario;
		this.perfil = perfil;
		this.cedula = cedula;
		this.nombres = nombre;
		this.apellidos = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.usuario = usuario;
		this.clave = clave;
		this.foto = foto;
		this.estado = estado;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombre) {
		this.nombres = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellido) {
		this.apellidos = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
