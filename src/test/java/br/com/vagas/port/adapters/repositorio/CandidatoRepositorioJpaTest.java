package br.com.vagas.port.adapters.repositorio;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.vagas.AbstractTest;
import br.com.vagas.domain.candidato.Candidato;
import br.com.vagas.domain.candidato.CandidatoId;

public class CandidatoRepositorioJpaTest extends AbstractTest {

	private CandidatoRepositorioJpa repositorio;
	
	@Mock
	private CandidatoRepositorioSpringData springData;
	
	private Candidato candidato;
	
	@Before
	public void setUp() {
		repositorio = new CandidatoRepositorioJpa(springData);
		this.candidato = mock(Candidato.class);
	}
	
	@Test
	public void salvar() {
		
		repositorio.salvar(candidato);
		verify(springData).save(candidato);
		
	}
	
	@Test
	public void obterPeloId() {
		
		given(candidato.candidatoId()).willReturn(new CandidatoId(CANDIDATO_ID));
		
		repositorio.obterPeloId(candidato.candidatoId());
		verify(springData).findByCandidatoId(candidato.candidatoId());
		
	}
	
}
