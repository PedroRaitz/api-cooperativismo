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

import com.example.cooperativismo.Cooperativismoapi.model.Pauta;
import com.example.cooperativismo.Cooperativismoapi.model.RelatorioVotos;
import com.example.cooperativismo.Cooperativismoapi.model.Sessao;
import com.example.cooperativismo.Cooperativismoapi.model.Voto;
import com.example.cooperativismo.Cooperativismoapi.service.PautaService;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private PautaService pautaService;

	@GetMapping
	public List<Pauta> listar() {
		return pautaService.listarService();

	}

	@GetMapping("/{pautaId}")
	public ResponseEntity<Pauta> buscar(@PathVariable Long pautaId) {
		Optional<Pauta> pauta = pautaService.listarPorId(pautaId);

		if (pauta.isPresent()) {
			return ResponseEntity.ok(pauta.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pauta adicionar(@Validated @RequestBody Pauta pauta) {
		return pautaService.adicionar(pauta);

	}

	@PutMapping("/{pautaId}")
	public ResponseEntity<Pauta> atualizar(@Validated @PathVariable Long pautaId, @RequestBody Pauta pauta) {

		if (!pautaService.existById(pautaId)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(pautaService.atualizar(pautaId, pauta));

	}

	@PostMapping("/sessao/{pautaId}/{totalSegundos}")
	public ResponseEntity<Sessao> abrir(@Validated @PathVariable Long pautaId,
			@Validated @PathVariable Long totalSegundos) {

		return ResponseEntity.ok(pautaService.abrirSessao(pautaId, totalSegundos));
	}

	@PostMapping("/sessao/{pautaId}")
	public ResponseEntity<Sessao> abrir(@Validated @PathVariable Long pautaId) {

		return ResponseEntity.ok(pautaService.abrirSessao(pautaId));
	}

	@PostMapping("/voto/{pautaId}/{associadoId}/{voto}")
	public Voto votar(@Validated @PathVariable Long pautaId, @Validated @PathVariable Long associadoId,
			@Validated @PathVariable Boolean voto) {
		return pautaService.registrarVoto(pautaId, associadoId, voto);

	}

	@GetMapping("/relatorio/{pautaId}")
	public RelatorioVotos contabilizar(@Validated @PathVariable Long pautaId) {
		return pautaService.contabiliza(pautaId);

	}

}
