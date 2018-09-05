package br.com.vagas.application.vaga;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.vagas.domain.VagaId;
import br.com.vagas.domain.candidato.CandidatoId;

public class NovaCandidaturaComando {

	@JsonProperty("id_vaga")
	private VagaId vagaId;
	
	@JsonProperty("id_pessoa")
	private CandidatoId candidatoId;

	public NovaCandidaturaComando(VagaId vagaId, CandidatoId candidatoId) {
		this.vagaId = vagaId;
		this.candidatoId = candidatoId;
	}
	
	@SuppressWarnings("unused")
	private NovaCandidaturaComando() {}

	public VagaId getVagaId() {
		return vagaId;
	}

	public CandidatoId getCandidatoId() {
		return candidatoId;
	}

	public void setVagaId(VagaId vagaId) {
		this.vagaId = vagaId;
	}

	public void setCandidatoId(CandidatoId candidatoId) {
		this.candidatoId = candidatoId;
	}
	
}
