package com.example.cooperativismo.Cooperativismoapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cooperativismo.Cooperativismoapi.model.Associado;
import com.example.cooperativismo.Cooperativismoapi.model.Votacao;
import com.example.cooperativismo.Cooperativismoapi.model.Voto;
import com.example.cooperativismo.Cooperativismoapi.repository.VotacaoRepository;

@Service
public class VotacaoService {

	@Autowired
	private VotacaoRepository votacaoRepository;

	@Autowired
	private AssociadoService associadoService;

	@Autowired
	private VotoService votoService;

	public Votacao atribuir(Votacao recebe) {
		List<Associado> listar = associadoService.listarService();

		for (Associado associado : listar) {
			Voto voto = new Voto();
			voto.setAssociado(associado);
			voto.setVotacao(recebe);
			votoService.adicionar(voto);
			voto = votoService.adicionar(voto);

		}

		return votacaoRepository.save(recebe);

	}

	public List<Votacao> listarService() {
		return votacaoRepository.findAll();
	}

	public Optional<Votacao> listarPorId(Long id) {
		Optional<Votacao> votacao = votacaoRepository.findById(id);
		return votacao;
	}

	public Votacao adicionar(Votacao recebe) {
		return votacaoRepository.save(recebe);
	}

	public Votacao atualizar(Long votacaoId, Votacao votacao) {

		votacao.setId(votacaoId);
		votacao = votacaoRepository.save(votacao);
		return votacao;

	}

	public boolean existById(Long id) {

		if (!votacaoRepository.existsById(id)) {
			return true;
		}
		return false;
	}

}
