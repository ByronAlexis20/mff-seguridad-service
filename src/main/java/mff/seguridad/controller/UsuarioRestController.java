package mff.seguridad.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mff.seguridad.entity.ResponseMessage;
import mff.seguridad.entity.Usuario;
import mff.seguridad.service.IPerfilService;
import mff.seguridad.service.IUsuarioService;
import mff.seguridad.util.DatosSesionUtil;

@RestController
@RequestMapping("/mff-seguridad/usuario")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPerfilService perfilService;
	
	@GetMapping(value = "/buscarActivos")
	public ResponseEntity<?> buscarActivos() {
		List<Usuario> data = null;
		Map<String, Object> response = new HashMap<>();
		try {
			data = usuarioService.buscarUsuariosActivos();
		} catch (DataAccessException e) {
			response.put("mensaje: ", DatosSesionUtil.mensajeErrorConsulta);
			response.put("error: ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (data == null || data.size() == 0) {
			response.put("mensaje: ", DatosSesionUtil.mensajeNoDatos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Usuario>>(data, HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@Valid @RequestBody Usuario usu, BindingResult result) {
		Usuario data = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			data = new Usuario();
			if(usu.getIdUsuario() != null)
				data.setIdUsuario(usu.getIdUsuario());
			else
				data.setIdUsuario(null);
			data.setApellidos(usu.getApellidos());
			data.setCedula(usu.getCedula());
			data.setClave(encoder.encode(usu.getClave()));
			data.setDireccion(usu.getDireccion());
			data.setEstado(usu.getEstado());
			data.setNombres(usu.getNombres());
			data.setPerfil(perfilService.buscarPorId(usu.getPerfil().getIdPerfil()));
			data.setTelefono(usu.getTelefono());
			data.setUsuario(usu.getUsuario());
			data = this.usuarioService.guardar(data);
		} catch (DataAccessException e) {
			response.put("mensaje", DatosSesionUtil.mensajeErrorGrabar);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", DatosSesionUtil.mensajeOkGrabar);
		response.put("usuario", data);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/upload/{idUsuario}")
	public ResponseEntity<ResponseMessage> uploadFile(@PathVariable Integer idUsuario, @RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			Usuario dataActual = this.usuarioService.buscarPorIdUsuario(idUsuario);
			dataActual.setFoto(file.getBytes());
			this.usuarioService.guardar(dataActual);
			message = "Imagen Subida con Exito!!: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Ocurrio un problema al Subir Imagen!!: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}
	
	@GetMapping("/obtenerImagenUsuario/{idUsuario}")
	public ResponseEntity<String> obtenerImagenUsuario(@PathVariable Integer idUsuario) {
		Usuario dataActual = this.usuarioService.buscarPorIdUsuario(idUsuario);
		String re = "";
		if(dataActual.getFoto() != null) {
			re = Base64Utils.encodeToString(dataActual.getFoto());
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + dataActual.getCedula() + "\"")
				.body(re);
	}
}