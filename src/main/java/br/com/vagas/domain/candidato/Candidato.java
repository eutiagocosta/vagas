package br.com.vagas.domain.candidato;

import static br.com.vagas.application.Assert.assertArgumentNotEmpty;
import static br.com.vagas.application.Assert.assertArgumentNotNull;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.vagas.domain.CalculadoraDistancia;
import br.com.vagas.domain.Nivel;

@Entity
@Table(name = "CANDIDATO")
public class Candidato {

	public static final String ERR_NOME_INVALIDO = "Nome não pode ser nulo.";
	public static final String ERR_PROFISSAO_INVALIDA = "Profissão não pode ser nulo.";
	public static final String ERR_LOCALIZACAO_INVALIDA = "Localização não pode ser nulo.";
	public static final String ERR_NIVEL_INVALIDO = "Nível não pode ser nulo.";
	
	@EmbeddedId
	private CandidatoId candidatoId;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "PROFISSAO")
	private String profissao;
	
	@Column(name = "LOCALIZACAO")
	@Enumerated(EnumType.STRING)
	private Localizacao localizacao;
	
	@Column(name = "NIVEL")
	@Enumerated(EnumType.STRING)
	private Nivel nivel;
	
	@Column(name = "SCORE")
	private Integer score;

	public Candidato(CandidatoId candidatoId, String nome, String profissao, Localizacao localizacao, Nivel nivel) {
		setCandidatoId(candidatoId);
		setNome(nome);
		setProfissao(profissao);
		setLocalizacao(localizacao);
		setNivel(nivel);
	}
	
	@SuppressWarnings("unused")
	private Candidato() {}

	public CandidatoId candidatoId() {
		return candidatoId;
	}

	public String nome() {
		return nome;
	}

	public String profissao() {
		return profissao;
	}

	public Localizacao localizacao() {
		return localizacao;
	}

	public Nivel nivel() {
		return nivel;
	}
	
	public Integer score() {
		return score;
	}
	
	public void calcularScore(Nivel nivelVaga, Localizacao localizacaoVaga) {
		
		final Integer nivel = obterNivel(nivelVaga);
		
		final Integer distancia = new CalculadoraDistancia().calcular(this.localizacao, localizacaoVaga);
		
		setScore((nivel + distancia) / 2);
		
	}

	private Integer obterNivel(Nivel nivelVaga) {
		final Integer nivel = (100 - 25) * (nivelVaga.valor() - nivel().valor());
		return nivel < 0 ? 0 : nivel;
	}

	private void setCandidatoId(CandidatoId candidatoId) {
		this.candidatoId = candidatoId;
	}

	private void setNome(String nome) {
		assertArgumentNotEmpty(nome, ERR_NOME_INVALIDO);
		this.nome = nome;
	}

	private void setProfissao(String profissao) {
		assertArgumentNotEmpty(profissao, ERR_PROFISSAO_INVALIDA);
		this.profissao = profissao;
	}

	private void setLocalizacao(Localizacao localizacao) {
		assertArgumentNotNull(localizacao, ERR_LOCALIZACAO_INVALIDA);
		this.localizacao = localizacao;
	}

	private void setNivel(Nivel nivel) {
		assertArgumentNotNull(nivel, ERR_NIVEL_INVALIDO);
		this.nivel = nivel;
	}
	
	private void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Candidato [candidatoId=" + candidatoId + ", nome=" + nome + ", profissao=" + profissao
				+ ", localizacao=" + localizacao + ", nivel=" + nivel + "]";
	}

}
