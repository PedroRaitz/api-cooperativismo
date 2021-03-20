package com.example.cooperativismo.Cooperativismoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cooperativismo.Cooperativismoapi.model.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

}
