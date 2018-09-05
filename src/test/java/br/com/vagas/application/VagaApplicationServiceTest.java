package br.com.vagas.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import br.com.vagas.AbstractTest;
import br.com.vagas.application.vaga.NovaCandidaturaComando;
import br.com.vagas.application.vaga.NovaVagaComando;
import br.com.vagas.application.vaga.VagaApplicationService;
import br.com.vagas.application.vaga.data.CandidaturaData;
import br.com.vagas.domain.Nivel;
import br.com.vagas.domain.Vaga;
import br.com.vagas.domain.VagaId;
import br.com.vagas.domain.VagaRepositorio;
import br.com.vagas.domain.candidato.CandidatoId;
import br.com.vagas.domain.candidato.Localizacao;
import br.com.vagas.domain.exception.RecursoNaoEncontradoException;

public class VagaApplicationServiceTest extends AbstractTest{

	private VagaApplicationService service;
	
	@Mock
	private VagaRepositorio repositorio;
	
	@Before
	public void setUp() {
		
		service = new VagaApplicationService(repositorio);
	}
	
	@Test
	public void registrarVaga() {
		
		final VagaId vagaId = service.registrarVaga(new NovaVagaComando(
				"Vagas.com", 
				"Desenvolvedor Java", 
				"Desenvolver aplicações",
				"A", 
				3));
		
		ArgumentCaptor<Vaga> argumentCaptor = ArgumentCaptor.forClass(Vaga.class);
    	
    	verify(repositorio).salvar(argumentCaptor.capture());
    	
    	Vaga vaga = argumentCaptor.getValue();
    	
    	assertEquals(vagaId, vaga.vagaId());
    	assertEquals("Vagas.com", vaga.empresa());
    	assertEquals("Desenvolvedor Java", vaga.titulo());
    	assertEquals("Desenvolver aplicações", vaga.descricao());
    	assertEquals(Localizacao.A, vaga.localizacao());
    	assertEquals(Nivel.PLENO, vaga.nivel());
    	
	}
	
	@Test
	public void registrarCandidaturaParaVaga() {
		
		Vaga vaga = mock(Vaga.class);
		
		given(repositorio.obterPeloId(new VagaId(VAGA_ID)))
			.willReturn(Optional.of(vaga));
		
		service.registrarCandidatura(new NovaCandidaturaComando(new VagaId(VAGA_ID),
				new CandidatoId(CANDIDATO_ID)));
		
		verify(vaga).registrarCandidatura(new CandidatoId(CANDIDATO_ID));
		verify(repositorio).salvar(vaga);
		
	}
	
	@Test
	public void registrarCandidaturaParaVagaDadoVagaNaoEncontradaDeveLancarException() {

		Vaga vaga = mock(Vaga.class);

		given(repositorio.obterPeloId(new VagaId(VAGA_ID)))
			.willReturn(Optional.empty());

		assertThatThrownBy(() -> service.registrarCandidatura(new NovaCandidaturaComando(
				new VagaId(VAGA_ID), 
				new CandidatoId(CANDIDATO_ID))))
					.hasMessage(VagaApplicationService.ERR_VAGA_INVALIDA)
					.isInstanceOf(RecursoNaoEncontradoException.class);

		verify(vaga, times(0)).registrarCandidatura(new CandidatoId(CANDIDATO_ID));

	}
	
	@Test
	public void listarCandidaturas() {
		
		given(repositorio.obterPeloId(new VagaId(VAGA_ID)))
			.willReturn(construirVagasComCandidaturas(new VagaId(VAGA_ID)));
		
		List<CandidaturaData> candidaturas = service.listarCandidaturas(new VagaId(VAGA_ID));
		
		assertEquals(3, candidaturas.size());
		assertEquals(Integer.valueOf(87), candidaturas.get(0).getScore());
		assertEquals(Integer.valueOf(37), candidaturas.get(1).getScore());
		assertEquals(Integer.valueOf(12), candidaturas.get(2).getScore());
		
	}
	
	@Test
	public void listarCandidaturasDadoVagaNaoEncontradoDeveLancarException() {
		
		given(repositorio.obterPeloId(new VagaId(VAGA_ID)))
			.willReturn(Optional.empty());
		
		assertThatThrownBy(() -> service.listarCandidaturas(new VagaId(VAGA_ID)))
			.hasMessage(VagaApplicationService.ERR_VAGA_INVALIDA)
			.isInstanceOf(RecursoNaoEncontradoException.class);
		
	}

}
