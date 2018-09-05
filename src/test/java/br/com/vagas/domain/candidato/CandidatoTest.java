package br.com.vagas.domain.candidato;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.vagas.AbstractTest;
import br.com.vagas.domain.Nivel;
import br.com.vagas.domain.exception.ArgumentoInvalidoException;

public class CandidatoTest extends AbstractTest {
	
	@Test
	public void novoCandidato() {
		
		final Candidato candidato = new Candidato(new CandidatoId(CANDIDATO_ID),
				"Tiago Costa",
				"Desenvolvedor Java",
				Localizacao.C,
				Nivel.SENIOR);
		
		assertEquals(new CandidatoId(CANDIDATO_ID), candidato.candidatoId());
		assertEquals("Tiago Costa", candidato.nome());
		assertEquals("Desenvolvedor Java", candidato.profissao());
		assertEquals(Localizacao.C, candidato.localizacao());
		assertEquals(Nivel.SENIOR, candidato.nivel());
		
	}
	
	@Test
	public void novoCandidatoDadoProfissaoNuloDeveLancarException() {
		
		assertThatThrownBy(() -> new Candidato(new CandidatoId(CANDIDATO_ID),
				"Tiago Costa",
				null,
				Localizacao.C,
				Nivel.SENIOR))
		.hasMessage(Candidato.ERR_PROFISSAO_INVALIDA)
		.isInstanceOf(ArgumentoInvalidoException.class);
		
	}
	
	@Test
	public void novoCandidatoDadoLocalizacaoNuloDeveLancarException() {
		
		assertThatThrownBy(() -> new Candidato(new CandidatoId(CANDIDATO_ID),
				"Tiago Costa",
				"Desenvolvedor Java",
				null,
				Nivel.SENIOR))
		.hasMessage(Candidato.ERR_LOCALIZACAO_INVALIDA)
		.isInstanceOf(ArgumentoInvalidoException.class);
		
	}
	
	@Test
	public void novoCandidatoDadoNivelNuloDeveLancarException() {
		
		assertThatThrownBy(() -> new Candidato(new CandidatoId(CANDIDATO_ID),
				"Tiago Costa",
				"Desenvolvedor Java",
				Localizacao.C,
				null))
		.hasMessage(Candidato.ERR_NIVEL_INVALIDO)
		.isInstanceOf(ArgumentoInvalidoException.class);
		
	}
	
	@Test
	public void calcularScore() {
		
		final Candidato candidato = new Candidato(new CandidatoId(CANDIDATO_ID),
				"Tiago Costa",
				"Desenvolvedor Java",
				Localizacao.C,
				Nivel.JUNIOR);
		
		candidato.calcularScore(Nivel.PLENO, Localizacao.A);
		
		assertEquals(Integer.valueOf(62), candidato.score());
		
	}
	
	@Test
	public void calcularScoreDadoNivelForNegativoDeveRetornarZero() {
		
		final Candidato candidato = new Candidato(new CandidatoId(CANDIDATO_ID),
				"Tiago Costa",
				"Desenvolvedor Java",
				Localizacao.F,
				Nivel.ESPECIALISTA);
		
		candidato.calcularScore(Nivel.PLENO, Localizacao.A);
		
		assertEquals(Integer.valueOf(12), candidato.score());
		
	}

}
