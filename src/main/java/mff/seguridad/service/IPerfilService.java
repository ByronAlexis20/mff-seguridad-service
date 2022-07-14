package mff.seguridad.service;

import java.util.List;

import mff.seguridad.entity.Perfil;

public interface IPerfilService {

	public List<Perfil> buscarPerfilesActivos();
	public List<Perfil> buscarTodosPerfiles();
	public Perfil buscarPorId(Integer idPerfil);
}
