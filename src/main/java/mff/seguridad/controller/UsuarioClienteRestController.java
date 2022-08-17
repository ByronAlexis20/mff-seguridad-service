package mff.seguridad.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mff.seguridad.entity.Cliente;
import mff.seguridad.service.IClienteService;
import mff.seguridad.util.DatosSesionUtil;

@RestController
@RequestMapping("/mff-seguridad/usuariocliente")
public class UsuarioClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Cliente cli, BindingResult result) {
		Cliente data = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			if(this.clienteService.verificarCorreoExistente(cli.getCorreo())) {
				response.put("mensaje", "Correo ingresado ya existe");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			data = new Cliente();
			if(cli.getIdCliente() != null)
				data.setIdCliente(cli.getIdCliente());
			else
				data.setIdCliente(null);
			data.setApellidos(cli.getApellidos());
			data.setCedula(cli.getCedula());
			data.setClave(encoder.encode(cli.getClave()));
			data.setCelular(cli.getCelular());
			data.setCorreo(cli.getCorreo());
			data.setFechaNacimiento(cli.getFechaNacimiento());
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
