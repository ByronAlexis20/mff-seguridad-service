package mff.seguridad.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mff.seguridad.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Integer> {

	public Optional<Usuario> findByUsuario(String usuario);
	
	@Query("Select u from Usuario u where u.estado = 'A' and u.usuario = ?1")
	public Usuario buscarPorUsuario(String usuario);
	
	@Query("Select u from Usuario u where u.estado = 'A' and u.idUsuario = ?1")
	public Usuario buscarPorIdUsuario(Integer idUsuario);
	
	@Query("Select u from Usuario u where u.estado = 'A'")
	public List<Usuario> buscarUsuariosActivos();
}
