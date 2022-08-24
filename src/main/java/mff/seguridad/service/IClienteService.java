package mff.seguridad.service;

import mff.seguridad.entity.Cliente;

public interface IClienteService {

	public Cliente grabar(Cliente cliente);
	
	public Boolean verificarCorreoExistente(String correo);
	
	public Boolean verificarCedulaExistente(String cedula);
	
}
