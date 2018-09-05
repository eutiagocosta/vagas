package br.com.vagas.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import br.com.vagas.AbstractTest;
import br.com.vagas.application.candidato.CandidatoApplicationService;
import br.com.vagas.application.candidato.NovoCandidatoComando;
import br.com.vagas.domain.Nivel;
import br.com.vagas.domain.candidato.Candidato;
import br.com.vagas.domain.candidato.CandidatoId;
import br.com.vagas.domain.candidato.CandidatoRepositorio;
import br.com.vagas.domain.candidato.Localizacao;

public class CandidatoApplicationServiceTest extends AbstractTest {

	private CandidatoApplicationService candidatoApplicationService;
	
	@Mock
	private CandidatoRepositorio repository;
	
	@Before
	public void setUp() {
		this.candidatoApplicationService = new CandidatoApplicationService(repository);
	}
	
	@Test
	public void registrarCandidato() {
		
		final CandidatoId candidatoId = candidatoApplicationService.registrarCandidato(new NovoCandidatoComando(
				"Tiago Costa",
				"Engenheiro de Software",
				"C",
				4));
		
		ArgumentCaptor<Candidato> argumentCaptor = ArgumentCaptor.forClass(Candidato.class);
		
		verify(repository).salvar(argumentCaptor.capture());
		
		Candidato candidato = argumentCaptor.getValue();
		
		assertEquals(candidatoId, candidato.candidatoId());
		assertEquals("Tiago Costa", candidato.nome());
		assertEquals("Engenheiro de Software", candidato.profissao());
		assertEquals(Localizacao.C, candidato.localizacao());
		assertEquals(Nivel.SENIOR, candidato.nivel());
		
	}
}
