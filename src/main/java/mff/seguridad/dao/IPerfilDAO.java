package mff.seguridad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mff.seguridad.entity.Perfil;

public interface IPerfilDAO extends CrudRepository<Perfil, Integer> {

	@Query("Select p from Perfil p where p.estado = 'A'")
	public List<Perfil> buscarPerfilesActivos();
	
	@Query("Select p from Perfil p ")
	public List<Perfil> buscarTodosPerfiles();
	
	@Query("Select p from Perfil p where p.idPerfil = ?1 ")
	public Perfil buscarPorId(Integer idPerfil);
}
