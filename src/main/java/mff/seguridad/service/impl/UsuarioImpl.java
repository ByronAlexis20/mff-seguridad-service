package mff.seguridad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mff.seguridad.dao.IUsuarioDAO;
import mff.seguridad.entity.Usuario;
import mff.seguridad.service.IUsuarioService;

@Service
public class UsuarioImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	@Transactional(readOnly = true)
	public Usuario buscarPorUsuario(String usuario) {
		return this.usuarioDAO.buscarPorUsuario(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarPorIdUsuario(Integer idUsuario) {
		return this.usuarioDAO.buscarPorIdUsuario(idUsuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> buscarUsuariosActivos() {
		return this.usuarioDAO.buscarUsuariosActivos();
	}

	@Override
	@Transactional
	public Usuario guardar(Usuario usuario) {
		return this.usuarioDAO.save(usuario);
	}

}