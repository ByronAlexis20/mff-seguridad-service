package mff.seguridad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mff.seguridad.entity.Permiso;

public interface IPermisoDAO extends CrudRepository<Permiso, Integer> {
	
	@Query("Select p from Permiso p where p.estado = 'A' and p.perfil.idPerfil = ?1")
	public List<Permiso> buscarPorIdPerfil(Integer idPerfil);
	
}
