package br.com.vagas.port.adapters.repositorio;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.vagas.AbstractTest;
import br.com.vagas.domain.Nivel;
import br.com.vagas.domain.candidato.Candidato;
import br.com.vagas.domain.candidato.CandidatoId;
import br.com.vagas.domain.candidato.Localizacao;

@DataJpaTest
public class CandidatoRepositorioSpringDataTest extends AbstractTest {
	
	@Autowired
	protected TestEntityManager em;
	
	@Autowired
	private CandidatoRepositorioSpringData repositorio;
	
	private Candidato candidato;
	
	@Before
	public void setUp() {
		
		this.candidato = new Candidato(new CandidatoId(CANDIDATO_ID),
				"Tiago Costa",
				"Desenvolvedor Java",
				Localizacao.C,
				Nivel.SENIOR);
		
	}
	
	@Test
	public void salvar() {
		
		repositorio.save(candidato);
		
		final Candidato candidatoEncontrado = em.find(Candidato.class, em.getId(candidato));
		
		assertEquals(candidato.candidatoId(), candidatoEncontrado.candidatoId());
		
	}
	
	@Test
	public void findByCandidatoId() {
		
		em.persist(candidato);

		final Candidato candidatoEncontrado = repositorio.findByCandidatoId(new CandidatoId(CANDIDATO_ID)).get();
		
		assertEquals(candidato.candidatoId(), candidatoEncontrado.candidatoId());
		
	}
	
}
