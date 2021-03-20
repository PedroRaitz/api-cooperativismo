package com.example.cooperativismo.Cooperativismoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cooperativismo.Cooperativismoapi.model.Sessao;
import com.example.cooperativismo.Cooperativismoapi.repository.SessaoRepository;

@Service
public class SessaoService {

	@Autowired
	private SessaoRepository sessaoRepository;

	public List<Sessao> listarService() {
		return sessaoRepository.findAll();
	}

	public Optional<Sessao> listarPorId(Long id) {
		Optional<Sessao> sessao = sessaoRepository.findById(id);
		return sessao;
	}

	public Sessao adicionar(Sessao recebe) {
		return sessaoRepository.save(recebe);
	}

	public Sessao atualizar(Long sessaoId, Sessao sessao) {

		sessao.setId(sessaoId);
		sessao = sessaoRepository.save(sessao);
		return sessao;

	}

	public boolean existById(Long id) {

		if (!sessaoRepository.existsById(id)) {
			return true;
		}
		return false;
	}

}
