package com.startjwtproject.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.startjwtproject.model.User;
import com.startjwtproject.repository.UserRepository;
import com.startjwtproject.service.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {

	private final UserRepository repository;

	private final UserService service;

	private final PasswordEncoder encoder;

	public UserController(UserRepository repository, PasswordEncoder encoder) {

		this.repository = repository;
		this.encoder = encoder;
		this.service = new UserService(repository);
	}

	@GetMapping(value = "teste", produces = MediaType.APPLICATION_ATOM_XML_VALUE)
	public ResponseEntity<String> teste() {

		String c = "Olá Mundo";

		return ResponseEntity.ok(c.toString());
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> findAll(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000") Integer size,
			HttpServletResponse response) {

		return ResponseEntity.ok(repository.listarTodos(page, size, ""));
	}

	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serializable> findById(@PathVariable("id") @Valid UUID id, HttpServletResponse response) {

		User u = repository.findById("id_user", id);

		return ResponseEntity.ok(u);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> salvar(@RequestBody @Valid User user) {
		user.setPass(encoder.encode(user.getPass()));
		user.setPass(user.getPass());
		return ResponseEntity.ok(repository.persist(user));
	}

	@PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serializable> editar(@PathVariable("id") UUID id, @Valid @RequestBody User user,
			HttpServletResponse response) throws IOException {

		User updateUser = repository.findById("id_user", id);

		updateUser.setName(user.getName());
		updateUser.setEmail(user.getEmail());
		updateUser.setPass(encoder.encode(user.getPass()));
		updateUser.setPass(user.getPass());
		updateUser.setSince(user.getSince());

		repository.persist(updateUser);

		return ResponseEntity.ok(repository.persist(updateUser));
	}

	@DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletar(@PathVariable UUID id, HttpServletResponse response) throws IOException {
		boolean b = service.delete(id);

		JSONObject obj = new JSONObject();

		if (b) {
			obj.put("status", "Deletado com sucesso");
			return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
		} else {
			obj.put("status", "Problemas ao deletar");
			return new ResponseEntity<String>(obj.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public PasswordEncoder getEncoder() {
		return encoder;
	}

}
