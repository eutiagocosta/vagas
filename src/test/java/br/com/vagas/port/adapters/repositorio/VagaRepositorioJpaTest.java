package br.com.vagas.port.adapters.repositorio;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.vagas.AbstractTest;
import br.com.vagas.domain.Vaga;
import br.com.vagas.domain.VagaId;

public class VagaRepositorioJpaTest extends AbstractTest {

	private VagaRepositorioJpa repositorio;
	
	@Mock
	private VagaRepositorioSpringData springData;
	
	private Vaga vaga;
	
	@Before
	public void setUp() {
		repositorio = new VagaRepositorioJpa(springData);
		this.vaga = mock(Vaga.class);
	}
	
	@Test
	public void salvar() {
		
		repositorio.salvar(vaga);
		verify(springData).save(vaga);
		
	}
	
	@Test
	public void obterPeloId() {
		
		given(vaga.vagaId()).willReturn(new VagaId(VAGA_ID));
		
		repositorio.obterPeloId(vaga.vagaId());
		verify(springData).findByVagaId(vaga.vagaId());
		
	}
	
}
