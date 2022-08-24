package mff.seguridad.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mff.seguridad.dto.ClienteDTO;
import mff.seguridad.entity.Cliente;
import mff.seguridad.service.IClienteService;
import mff.seguridad.util.DatosSesionUtil;
import mff.seguridad.util.FuncionesGenerales;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/seguridad/usuariocliente")
public class UsuarioClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody ClienteDTO cli) {
		Cliente data = null;
		Map<String, Object> response = new HashMap<>();
		try {
			FuncionesGenerales fun = new FuncionesGenerales();
			if(!fun.validadorDeCedula(cli.getCedula())) {
				response.put("mensaje", "Cedula incorrecta");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
			
			if(this.clienteService.verificarCedulaExistente(cli.getCedula())) {
				response.put("mensaje", "Cedula ingresado ya existe");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
			
			if(this.clienteService.verificarCorreoExistente(cli.getCorreo())) {
				response.put("mensaje", "Correo ingresado ya existe");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			data = new Cliente();
			if(cli.getId() != null)
				data.setIdCliente(cli.getId());
			else
				data.setIdCliente(null);
			data.setApellidos(cli.getApellidos());
			data.setCedula(cli.getCedula());
			data.setClave(encoder.encode(cli.getClave()));
			data.setCelular(cli.getCelular());
			data.setCorreo(cli.getCorreo());
			data.setEstado("A");
			data.setNombres(cli.getNombres());
			data = this.clienteService.grabar(data);
		} catch (DataAccessException e) {
			response.put("mensaje", DatosSesionUtil.mensajeErrorGrabar);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente registrado con éxito");
		response.put("cliente", data);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
