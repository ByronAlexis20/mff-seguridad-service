package mff.seguridad.dto;

public class ClienteDTO {

	private Integer id;
	private String cedula;
	private String celular;
	private String nombres;
	private String apellidos;
	private String correo;
	private String clave;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Integer id, String cedula, String celular, String nombres, String apellidos, String correo,
			String clave) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.celular = celular;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.clave = clave;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
