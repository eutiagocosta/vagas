package br.com.vagas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import br.com.vagas.AbstractTest;
import br.com.vagas.domain.candidato.Candidato;
import br.com.vagas.domain.candidato.CandidatoId;
import br.com.vagas.domain.candidato.CandidatoRepositorio;
import br.com.vagas.domain.candidato.Localizacao;
import br.com.vagas.domain.exception.ArgumentoInvalidoException;
import br.com.vagas.domain.exception.RecursoNaoEncontradoException;

public class VagaTest extends AbstractTest{
	
	CandidatoRepositorio candidatoRepositorio = mock(CandidatoRepositorio.class);
	
	@Before
	public void setUp() {
		RegistroDominio.defineCandidatoRepositorio(candidatoRepositorio);
	}
	
	@Test
	public void novaVaga() {
		
		final Vaga vaga = new Vaga(new VagaId(VAGA_ID),
				"Vagas.com",
				"Desenvolvedor Java",
				"Desenvolver aplicações",
				Localizacao.A,
				Nivel.PLENO);
		
		assertEquals(new VagaId(VAGA_ID), vaga.vagaId());
		assertEquals("Vagas.com", vaga.empresa());
		assertEquals("Desenvolvedor Java", vaga.titulo());
		assertEquals("Desenvolver aplicações", vaga.descricao());
		assertEquals(Localizacao.A, vaga.localizacao());
		assertEquals(Nivel.PLENO, vaga.nivel());
		assertEquals(Integer.valueOf(0), vaga.totalCandidatos());
		
	}
	
	@Test
	public void novaVagaComCandidatos() {
		
		final Vaga vaga = new Vaga(new VagaId(VAGA_ID),
				"Vagas.com",
				"Desenvolvedor Java",
				"Desenvolver aplicações",
				Localizacao.B,
				Nivel.PLENO,
				new HashSet<Candidato>(Arrays.asList(new Candidato(new CandidatoId(CANDIDATO_ID), 
						"Tiago Costa", 
						"Desenvolvedor Java",
						Localizacao.A,
						Nivel.SENIOR))));
		
		assertEquals(new VagaId(VAGA_ID), vaga.vagaId());
		assertEquals("Vagas.com", vaga.empresa());
		assertEquals("Desenvolvedor Java", vaga.titulo());
		assertEquals("Desenvolver aplicações", vaga.descricao());
		assertEquals(Nivel.PLENO, vaga.nivel());
		assertEquals(Integer.valueOf(1), vaga.totalCandidatos());
		
	}
	
	@Test
	public void novaVagaDadoEmpresaNuloDeveLancarException() {
		
		assertThatThrownBy(() -> new Vaga(new VagaId(VAGA_ID),
				null,
				"Desenvolvedor Java",
				"Desenvolver aplicações",
				Localizacao.B,
				Nivel.PLENO))
		.hasMessage(Vaga.ERR_EMPRESA_INVALIDA)
		.isInstanceOf(ArgumentoInvalidoException.class);
		
	}
	
	@Test
	public void novaVagaDadoTituloNuloDeveLancarException() {
		
		assertThatThrownBy(() -> new Vaga(new VagaId(VAGA_ID),
				"Vagas.com",
				null,
				"Desenvolver aplicações",
				Localizacao.B,
				Nivel.PLENO))
		.hasMessage(Vaga.ERR_TITULO_INVALIDO)
		.isInstanceOf(ArgumentoInvalidoException.class);
		
	}
	
	@Test
	public void novaVagaDadoDescricaoNuloDeveLancarException() {
		
		assertThatThrownBy(() -> new Vaga(new VagaId(VAGA_ID),
				"Vagas.com",
				"Desenvolvedor Java",
				null,
				Localizacao.B,
				Nivel.PLENO))
		.hasMessage(Vaga.ERR_DESCRICAO_INVALIDA)
		.isInstanceOf(ArgumentoInvalidoException.class);
		
	}
	
	@Test
	public void novaVagaDadoNivelNuloDeveLancarException() {
		
		assertThatThrownBy(() -> new Vaga(new VagaId(VAGA_ID),
				"Vagas.com",
				"Desenvolvedor Java",
				"Desenvolver aplicações",
				Localizacao.B,
				null))
		.hasMessage(Vaga.ERR_NIVEL_INVALIDO)
		.isInstanceOf(ArgumentoInvalidoException.class);
		
	}
	
	@Test
	public void novaVagaDadoLocalizaoNuloDeveLancarException() {
		
		assertThatThrownBy(() -> new Vaga(new VagaId(VAGA_ID),
				"Vagas.com",
				"Desenvolvedor Java",
				"Desenvolver aplicações",
				null,
				Nivel.PLENO))
		.hasMessage(Vaga.ERR_LOCALIZACAO_INVALIDO)
		.isInstanceOf(ArgumentoInvalidoException.class);
		
	}
	
	@Test
	public void registrarCandidatura() {
		
		Candidato candidato = mock(Candidato.class);
		
		given(candidatoRepositorio.obterPeloId(new CandidatoId(CANDIDATO_ID)))
			.willReturn(Optional.of(candidato));
		
		final Vaga vaga = new Vaga(new VagaId(VAGA_ID),
				"Vagas.com",
				"Desenvolvedor Java",
				"Desenvolver aplicações",
				Localizacao.A,
				Nivel.PLENO);
		
		vaga.registrarCandidatura(new CandidatoId(CANDIDATO_ID));
		
		assertEquals(1, vaga.candidatos().size());
		
		//TODO verificar se publicou o evento NovaCandidaturaRegistradaEvento
		
	}
	
	@Test
	public void registrarCandidaturaDadoCandidatoNaoEncontradoDeveLancarException() {

		given(candidatoRepositorio.obterPeloId(new CandidatoId(CANDIDATO_ID)))
			.willReturn(Optional.empty());

		final Vaga vaga = new Vaga(new VagaId(VAGA_ID), 
				"Vagas.com",
				"Desenvolvedor Java", 
				"Desenvolver aplicações",
				Localizacao.A,
				Nivel.PLENO);

		assertThatThrownBy(() -> vaga.registrarCandidatura(new CandidatoId(CANDIDATO_ID)))
				.hasMessage(Vaga.ERR_CANDIDATO_INVALIDO)
				.isInstanceOf(RecursoNaoEncontradoException.class);

		assertEquals(0, vaga.candidatos().size());

	}
	
}
