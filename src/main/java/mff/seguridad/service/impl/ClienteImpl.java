package mff.seguridad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mff.seguridad.dao.IClienteDAO;
import mff.seguridad.entity.Cliente;
import mff.seguridad.service.IClienteService;

@Service
public class ClienteImpl implements IClienteService {

	@Autowired
	private IClienteDAO clienteDao;
	
	@Override
	@Transactional
	public Cliente grabar(Cliente cliente) {
		return this.clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Boolean verificarCorreoExistente(String correo) {
		Boolean bandera = false;
		List<Cliente> lista = this.clienteDao.buscarPorCorreo(correo);
		if(lista.size() > 0)
			bandera = true;
		return bandera;
	}

}
