package com.example.cooperativismo.Cooperativismoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cooperativismo.Cooperativismoapi.model.Votacao;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

}
