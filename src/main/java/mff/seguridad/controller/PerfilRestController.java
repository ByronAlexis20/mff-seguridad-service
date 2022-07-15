package mff.seguridad.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mff.seguridad.entity.Perfil;
import mff.seguridad.service.IPerfilService;
import mff.seguridad.util.DatosSesionUtil;

@RestController
@RequestMapping("/mff-seguridad/perfil")
public class PerfilRestController {

	@Autowired
	private IPerfilService perfilService;
	
	@GetMapping(value = "/buscarActivos")
	public ResponseEntity<?> buscarActivos() {
		List<Perfil> data = null;
		Map<String, Object> response = new HashMap<>();
		try {
			data = perfilService.buscarPerfilesActivos();
		} catch (DataAccessException e) {
			response.put("mensaje: ", DatosSesionUtil.mensajeErrorConsulta);
			response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (data == null || data.size() == 0) {
			response.put("mensaje: ", DatosSesionUtil.mensajeNoDatos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Perfil>>(data, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarTodosPerfiles")
	public ResponseEntity<?> buscarTodosPerfiles() {
		List<Perfil> data = null;
		Map<String, Object> response = new HashMap<>();
		try {
			data = perfilService.buscarTodosPerfiles();
		} catch (DataAccessException e) {
			response.put("mensaje: ", DatosSesionUtil.mensajeErrorConsulta);
			response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (data == null || data.size() == 0) {
			response.put("mensaje: ", DatosSesionUtil.mensajeNoDatos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Perfil>>(data, HttpStatus.OK);
	}
}
