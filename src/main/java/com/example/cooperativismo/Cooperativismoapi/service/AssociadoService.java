package com.example.cooperativismo.Cooperativismoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cooperativismo.Cooperativismoapi.model.Associado;
import com.example.cooperativismo.Cooperativismoapi.repository.AssociadoRepository;

@Service
public class AssociadoService {

	@Autowired
	private AssociadoRepository associadoRepository;

	public List<Associado> listarService() {
		return associadoRepository.findAll();
	}

	public Optional<Associado> listarPorId(Long id) {
		Optional<Associado> associado = associadoRepository.findById(id);
		return associado;
	}

	public Associado adicionar(Associado recebe) {
		return associadoRepository.save(recebe);
	}

	public Associado atualizar(Long associadoId, Associado associado) {

		associado.setId(associadoId);
		associado = associadoRepository.save(associado);
		return associado;

	}

	public boolean existById(Long id) {

		if (!associadoRepository.existsById(id)) {
			return true;
		}
		return false;
	}

	public Optional<Associado> getAssociado(Long associadoId) {
		return associadoRepository.findById(associadoId);
	}
	
	
	
	
	
}
