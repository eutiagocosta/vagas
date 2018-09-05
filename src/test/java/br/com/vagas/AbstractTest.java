package br.com.vagas;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.vagas.domain.Nivel;
import br.com.vagas.domain.Vaga;
import br.com.vagas.domain.VagaId;
import br.com.vagas.domain.candidato.Candidato;
import br.com.vagas.domain.candidato.CandidatoId;
import br.com.vagas.domain.candidato.Localizacao;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractTest {
	
	protected static final String CANDIDATO_ID = "6DEA6D89-FC11-4537-95C1-079F4C354B01";
	
	protected static final String VAGA_ID = "5370C088-91F3-4BFF-B681-B8926617729E";
	
    protected MockMvc mockMvc;
	
	@Autowired
    protected WebApplicationContext wac;
	
	protected Optional<Vaga> construirVagasComCandidaturas(VagaId vagaId) {
		
		Candidato candidato1 = new Candidato(new CandidatoId(CANDIDATO_ID), 
				"Tiago Costa", 
				"Desenvolvedor Java",
				Localizacao.A,
				Nivel.SENIOR);
		candidato1.calcularScore(Nivel.ESPECIALISTA, Localizacao.A);
		
		Candidato candidato2 = new Candidato(new CandidatoId(CANDIDATO_ID), 
				"Tiago Costa", 
				"Desenvolvedor Java",
				Localizacao.A,
				Nivel.SENIOR);
		candidato2.calcularScore(Nivel.ESTAGIARIO, Localizacao.D);
		
		Candidato candidato3 = new Candidato(new CandidatoId(CANDIDATO_ID), 
				"Tiago Costa", 
				"Desenvolvedor Java",
				Localizacao.A,
				Nivel.SENIOR);
		candidato3.calcularScore(Nivel.SENIOR, Localizacao.F);
		
		return Optional.of(new Vaga(new VagaId(VAGA_ID),
				"Vagas.com",
				"Desenvolvedor Java",
				"Desenvolver aplicações",
				Localizacao.B,
				Nivel.PLENO,
				new HashSet<Candidato>(Arrays.asList(candidato1, candidato2, candidato3))));
		
	}
	
}
