package br.com.vagas.port.adapters.resource;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.vagas.AbstractTest;
import br.com.vagas.application.vaga.NovaVagaComando;
import br.com.vagas.application.vaga.VagaApplicationService;
import br.com.vagas.application.vaga.data.CandidaturaData;
import br.com.vagas.domain.VagaId;

public class VagaControllerTest extends AbstractTest {
    
	@Mock
	private VagaApplicationService service;
	
	@Autowired
	@InjectMocks
	private VagaController controller;
	
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
	@Test
	public void novaVaga() throws Exception {
		
		given(service.registrarVaga(
				new NovaVagaComando("Teste", "Vaga teste", "Criar os mais diferentes tipos de teste", "A", 3)))
						.willReturn(new VagaId(VAGA_ID));

		final String payload = "{\n" + 
				"\"empresa\": \"Teste\",\n" + 
				"\"titulo\": \"Vaga teste\",\n" + 
				"\"descricao\": \"Criar os mais diferentes tipos de teste\",\n" + 
				"\"localizacao\": \"A\",\n" + 
				"\"nivel\": 3\n" + 
				"}";
	
		mockMvc.perform(post("/v1/vagas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(header().string("Location", "http://localhost/v1/vagas/" + VAGA_ID))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	public void novaCandidatura() throws Exception {
		
		final String payload = "{\n" + 
				"\"id_vaga\": \"946220A6-C7A1-4781-BA34-25416CD9761B\",\n" + 
				"\"id_pessoa\": \"8B79638C-49CE-4075-8C0C-BF7AD7CFB001\"\n" + 
				"}";
		
		mockMvc.perform(post("/v1/candidaturas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(status().isOk());
	}
	
	@Test
	public void listarCandidaturas() throws Exception {
		
		given(service.listarCandidaturas(new VagaId(VAGA_ID)))
			.willReturn(Arrays.asList(new CandidaturaData(
					"João",
					"Analista de Sistemas",
					"D",
					4, 
					60),new CandidaturaData(
							"Pedro",
							"Analista de Qualidade",
							"A",
							5, 
							12)));
		
		mockMvc.perform(get("/v1/vagas/"+VAGA_ID+"/candidaturas/ranking")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].nome").value("João"))
				.andExpect(jsonPath("$[0].profissao").value("Analista de Sistemas"))
				.andExpect(jsonPath("$[0].localizacao").value("D"))
				.andExpect(jsonPath("$[0].nivel").value(4))
				.andExpect(jsonPath("$[0].score").value(60))
				.andExpect(jsonPath("$[1].nome").value("Pedro"))
				.andExpect(jsonPath("$[1].profissao").value("Analista de Qualidade"))
				.andExpect(jsonPath("$[1].localizacao").value("A"))
				.andExpect(jsonPath("$[1].nivel").value(5))
				.andExpect(jsonPath("$[1].score").value(12));
		
	}
	
}
