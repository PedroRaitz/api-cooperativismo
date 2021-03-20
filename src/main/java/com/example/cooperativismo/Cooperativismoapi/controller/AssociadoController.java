package com.example.cooperativismo.Cooperativismoapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cooperativismo.Cooperativismoapi.model.Associado;
import com.example.cooperativismo.Cooperativismoapi.service.AssociadoService;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

	@Autowired
	private AssociadoService associadoService;

	@GetMapping
	public List<Associado> listar() {
		return associadoService.listarService();

	}

	@GetMapping("/{associadoId}")
	public ResponseEntity<Associado> buscar(@PathVariable Long associadoId) {
		Optional<Associado> associado = associadoService.listarPorId(associadoId);

		if (associado.isPresent()) {
			return ResponseEntity.ok(associado.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Associado adicionar(@Validated @RequestBody Associado associado) {
		return associadoService.adicionar(associado);

	}

	@PutMapping("/{associadoId}")
	public ResponseEntity<Associado> atualizar(@Validated @PathVariable Long associadoId,
			@RequestBody Associado associado) {

		if (!associadoService.existById(associadoId)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(associadoService.atualizar(associadoId, associado));

	}

}
