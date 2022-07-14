package mff.seguridad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mff.seguridad.dao.IPerfilDAO;
import mff.seguridad.entity.Perfil;
import mff.seguridad.service.IPerfilService;

@Service
public class PerfilImpl implements IPerfilService {

	@Autowired
	private IPerfilDAO perfilDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Perfil> buscarPerfilesActivos() {
		return this.perfilDAO.buscarPerfilesActivos();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Perfil> buscarTodosPerfiles() {
		return this.perfilDAO.buscarTodosPerfiles();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Perfil buscarPorId(Integer idPerfil) {
		return this.perfilDAO.buscarPorId(idPerfil);
	}
}
