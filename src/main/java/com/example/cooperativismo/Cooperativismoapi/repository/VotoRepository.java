package com.example.cooperativismo.Cooperativismoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cooperativismo.Cooperativismoapi.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

}
