package com.example.cooperativismo.Cooperativismoapi.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Votacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "votacao")
	private Set<Voto> votos;

	@OneToOne(mappedBy = "votacao")
	private Pauta pauta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVotos(Set<Voto> votos) {
		this.votos = votos;
	}

	public Set<Voto> getVotos() {
		return this.votos;
	}

	public Voto addVoto(Voto voto) {
		this.getVotos().add(voto);
		voto.setVotacao(this);
		return voto;
	}

	public Voto removeVoto(Voto voto) {
		this.getVotos().remove(voto);
		voto.setVotacao(null);
		return voto;
	}

	

}
