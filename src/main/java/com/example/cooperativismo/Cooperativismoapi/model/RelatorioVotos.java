package com.example.cooperativismo.Cooperativismoapi.model;

public class RelatorioVotos {

	private Integer totalSim;

	private Integer totalNao;

	private String nomePauta;

	public String getNomePauta() {
		return nomePauta;
	}

	public void setNomePauta(String nomePauta) {
		this.nomePauta = nomePauta;
	}

	public Integer getTotalSim() {
		return totalSim;
	}

	public void setTotalSim(Integer totalSim) {
		this.totalSim = totalSim;
	}

	public Integer getTotalNao() {
		return totalNao;
	}

	public void setTotalNao(Integer totalNao) {
		this.totalNao = totalNao;
	}

}
