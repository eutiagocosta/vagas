package br.com.vagas.domain;

import static br.com.vagas.application.Assert.assertArgumentNotEmpty;
import static br.com.vagas.application.Assert.assertArgumentNotNull;
import static br.com.vagas.domain.RegistroDominio.candidatoRepositorio;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.vagas.domain.candidato.Candidato;
import br.com.vagas.domain.candidato.CandidatoId;
import br.com.vagas.domain.candidato.Localizacao;
import br.com.vagas.domain.exception.RecursoNaoEncontradoException;

@Entity
@Table(name = "VAGA")
public class Vaga {

	public static final String ERR_NIVEL_INVALIDO = "Nível não pode ser nulo.";
	public static final String ERR_DESCRICAO_INVALIDA = "Descrição não pode ser nulo.";
	public static final String ERR_TITULO_INVALIDO = "Título não pode ser nulo.";
	public static final String ERR_EMPRESA_INVALIDA = "Empresa não pode ser nulo.";
	public static final String ERR_LOCALIZACAO_INVALIDO = "Localização não pode ser nulo.";
	public static final String ERR_CANDIDATO_INVALIDO = "Candidato não encontrado.";
	
	@EmbeddedId
	private VagaId vagaId;
	
	@Column(name = "EMPRESA")
	private String empresa;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "LOCALIZACAO")
	@Enumerated(EnumType.STRING)
	private Localizacao localizacao;
	
	@Column(name = "NIVEL")
	@Enumerated(EnumType.STRING)
	private Nivel nivel;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "VAGA_ID")
	private Set<Candidato> candidatos;

	public Vaga(VagaId vagaId, String empresa, String titulo, String descricao, Localizacao localizacao, Nivel nivel,
			Set<Candidato> candidatos) {
		setVagaId(vagaId);
		setEmpresa(empresa);
		setTitulo(titulo);
		setDescricao(descricao);
		setLocalizacao(localizacao);
		setNivel(nivel);
		setCandidatos(candidatos);
	}
	
	public Vaga(VagaId vagaId, String empresa, String titulo, String descricao, Localizacao localizacao, Nivel nivel) {
		this(vagaId, empresa, titulo, descricao, localizacao, nivel, new HashSet<>());
	}

	@SuppressWarnings("unused")
	private Vaga() {}

	public VagaId vagaId() {
		return vagaId;
	}

	public String empresa() {
		return empresa;
	}

	public String titulo() {
		return titulo;
	}

	public String descricao() {
		return descricao;
	}
	
	public Localizacao localizacao() {
		return localizacao;
	}

	public Nivel nivel() {
		return nivel;
	}

	public Set<Candidato> candidatos() {
		if (candidatos == null)
			candidatos = new HashSet<>();
		return Collections.unmodifiableSet(candidatos);
	}
	
	public Integer totalCandidatos() {
		return candidatos().size();
	}
	
	private void setVagaId(VagaId vagaId) {
		this.vagaId = vagaId;
	}

	private void setEmpresa(String empresa) {
		assertArgumentNotEmpty(empresa, ERR_EMPRESA_INVALIDA);
		this.empresa = empresa;
	}

	private void setTitulo(String titulo) {
		assertArgumentNotEmpty(titulo, ERR_TITULO_INVALIDO);
		this.titulo = titulo;
	}

	private void setDescricao(String descricao) {
		assertArgumentNotEmpty(descricao, ERR_DESCRICAO_INVALIDA);
		this.descricao = descricao;
	}

	private void setNivel(Nivel nivel) {
		assertArgumentNotNull(nivel, ERR_NIVEL_INVALIDO);
		this.nivel = nivel;
	}

	private void setCandidatos(Set<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	private void setLocalizacao(Localizacao localizacao) {
		assertArgumentNotNull(localizacao, ERR_LOCALIZACAO_INVALIDO);
		this.localizacao = localizacao;
	}

	public void registrarCandidatura(CandidatoId candidatoId) {

		Candidato candidato = candidatoRepositorio().obterPeloId(candidatoId)
				.orElseThrow(() -> new RecursoNaoEncontradoException(ERR_CANDIDATO_INVALIDO));

		candidato.calcularScore(this.nivel, this.localizacao);
		
		candidatos.add(candidato);
		
		//TODO publicar NovaCandidaturaRegistradaEvento...
	}

}
