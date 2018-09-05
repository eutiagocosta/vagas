package br.com.vagas.port.adapters.repositorio;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.vagas.AbstractTest;
import br.com.vagas.domain.Nivel;
import br.com.vagas.domain.Vaga;
import br.com.vagas.domain.VagaId;
import br.com.vagas.domain.candidato.Localizacao;

@DataJpaTest
public class VagaRepositorioSpringDataTest extends AbstractTest {
	
	@Autowired
	protected TestEntityManager em;
	
	@Autowired
	private VagaRepositorioSpringData repositorio;
	
	private Vaga vaga;
	
	@Before
	public void setUp() {
		
		this.vaga = new Vaga(new VagaId(VAGA_ID),
				"Vagas.com",
				"Desenvolvedor Java",
				"Desenvolver aplicações",
				Localizacao.A,
				Nivel.PLENO);
		
	}
	
	@Test
	public void salvar() {
		
		repositorio.save(vaga);
		
		final Vaga vagaEncontrado = em.find(Vaga.class, em.getId(vaga));
		
		assertEquals(vaga.vagaId(), vagaEncontrado.vagaId());
		
	}
	
	@Test
	public void findByVagaId() {
		
		em.persist(vaga);

		final Vaga  vagaEncontrado = repositorio.findByVagaId(new VagaId(VAGA_ID)).get();
		
		assertEquals(vaga.vagaId(), vagaEncontrado.vagaId());
		
	}
	
}
