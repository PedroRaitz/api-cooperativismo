package com.example.cooperativismo.Cooperativismoapi.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cooperativismo.Cooperativismoapi.model.Associado;
import com.example.cooperativismo.Cooperativismoapi.model.Pauta;
import com.example.cooperativismo.Cooperativismoapi.model.RelatorioVotos;
import com.example.cooperativismo.Cooperativismoapi.model.Sessao;
import com.example.cooperativismo.Cooperativismoapi.model.Votacao;
import com.example.cooperativismo.Cooperativismoapi.model.Voto;
import com.example.cooperativismo.Cooperativismoapi.repository.PautaRepository;

@Service
public class PautaService {

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private VotacaoService votacaoService;

	@Autowired
	private SessaoService sessaoService;

	@Autowired
	private AssociadoService associadoService;

	@Autowired
	private VotoService votoService;

	public List<Pauta> listarService() {
		return pautaRepository.findAll();
	}

	public Optional<Pauta> listarPorId(Long id) {
		Optional<Pauta> pauta = pautaRepository.findById(id);
		return pauta;
	}

	public Pauta adicionar(Pauta recebe) {
		Votacao v = new Votacao();
		v = votacaoService.adicionar(v);
		recebe.setVotacao(v);
		recebe = pautaRepository.save(recebe);

		return recebe;
	}

	public Pauta atualizar(Long pautaId, Pauta pauta) {

		pauta.setId(pautaId);
		pauta = pautaRepository.save(pauta);
		return pauta;

	}

	public boolean existById(Long id) {

		if (!pautaRepository.existsById(id)) {
			return true;
		}
		return false;
	}

	public Sessao abrirSessao(Long pautaId, Long totalSegundos) {
		Optional<Pauta> pauta = pautaRepository.findById(pautaId);
		if (pauta.isPresent()) {
			Sessao sessao = pauta.get().getSessao();
			if (sessao == null) {
				sessao = new Sessao();
			}
			sessao.setDuracao(totalSegundos);
			sessao.setDataInicio(System.currentTimeMillis());
			sessao = sessaoService.adicionar(sessao);
			Pauta p = pauta.get();
			p.setSessao(sessao);
			pautaRepository.save(p);

			sessao.setPauta(null);
			return sessao;
		}

		return null;
	}

	public Sessao abrirSessao(Long pautaId) {

		return abrirSessao(pautaId, 60L);

	}

	public Voto registrarVoto(Long pautaId, Long associadoId, Boolean voto) {
		Optional<Pauta> pauta = pautaRepository.findById(pautaId);
		if (pauta.isPresent()) {
			if (sassaoValida(pauta.get().getSessao())) {
				Votacao v = pauta.get().getVotacao();
				Optional<Associado> associado = associadoService.getAssociado(associadoId);
				if (associado.isPresent()) {
					Voto votoEntity = associado.get().getVoto();
					if(votoEntity == null) {
						votoEntity = new Voto();
						votoEntity.setAssociado(associado.get());
					}
					votoEntity.setVotacao(v);
					votoEntity.setVoto(voto ? "Sim" : "Não");
					votoEntity = votoService.adicionar(votoEntity);
					votoEntity.setVotacao(null);
					votoEntity.setAssociado(null);
					return votoEntity;
				}
			}

		}
		return null;

	}

	private boolean sassaoValida(Sessao sessao) {
		if (sessao != null) {
			Long inicio = sessao.getDataInicio();
			Long duracao = sessao.getDuracao();
			Long agora = System.currentTimeMillis();
			if (inicio + (duracao * 1000) >= agora) {
				return true;
			}
		}
		return false;
	}

	public RelatorioVotos contabiliza(Long pautaId) {
		Optional<Pauta> pauta = pautaRepository.findById(pautaId);
		int totalSim = 0;
		int totalNao = 0;
		if (pauta.isPresent()) {
			RelatorioVotos relatorio = new RelatorioVotos();
			Pauta p = pauta.get();
			Votacao votacao = p.getVotacao();
			Set<Voto> votos = votacao.getVotos();
			for (Voto voto : votos) {
				if (voto.getVoto().equalsIgnoreCase("Sim")) {
					totalSim++;
				} else {
					totalNao++;
				}
			}

			relatorio.setTotalSim(totalSim);
			relatorio.setTotalNao(totalNao);
			relatorio.setNomePauta(p.getPauta());

			return relatorio;
		}
		return null;

	}

}
