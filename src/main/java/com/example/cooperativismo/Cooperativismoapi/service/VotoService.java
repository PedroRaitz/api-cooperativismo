package com.example.cooperativismo.Cooperativismoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cooperativismo.Cooperativismoapi.model.Voto;
import com.example.cooperativismo.Cooperativismoapi.repository.VotoRepository;

@Service
public class VotoService {

	@Autowired
	private VotoRepository votoRepository;

	public List<Voto> listarService() {
		return votoRepository.findAll();
	}

	public Optional<Voto> listarPorId(Long id) {
		Optional<Voto> voto = votoRepository.findById(id);
		return voto;
	}

	public Voto adicionar(Voto recebe) {
		return votoRepository.save(recebe);
	}

	public Voto atualizar(Long votoId, Voto voto) {

		voto.setId(votoId);
		voto = votoRepository.save(voto);
		return voto;

	}

	public boolean existById(Long id) {

		if (!votoRepository.existsById(id)) {
			return true;
		}
		return false;
	}

}
