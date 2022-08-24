package mff.seguridad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mff.seguridad.entity.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Integer> {

	@Query("Select c from Cliente c where c.estado = 'A' and c.correo = ?1")
	public List<Cliente> buscarPorCorreo(String correo);
	
	@Query("Select c from Cliente c where c.estado = 'A' and c.cedula = ?1")
	public List<Cliente> buscarPorCedula(String cedula);
	
}
