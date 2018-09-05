package br.com.vagas.port.adapters.resource;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.vagas.AbstractTest;
import br.com.vagas.application.candidato.CandidatoApplicationService;
import br.com.vagas.application.candidato.NovoCandidatoComando;
import br.com.vagas.domain.candidato.CandidatoId;

public class CandidatoControllerTest extends AbstractTest {
    
	@Mock
	private CandidatoApplicationService service;
	
	@Autowired
	@InjectMocks
	private CandidatoController controller;
	
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
	@Test
	public void novoCandidato() throws Exception {
		
		given(service.registrarCandidato(new NovoCandidatoComando("John Doe", "Engenheiro de Software", "C", 2)))
			.willReturn(new CandidatoId(CANDIDATO_ID));
		
		final String payload = "{\n" + 
				"\"nome\": \"John Doe\",\n" + 
				"\"profissao\": \"Engenheiro de Software\",\n" + 
				"\"localizacao\": \"C\",\n" + 
				"\"nivel\": 2\n" + 
				"}";
	
		mockMvc.perform(post("/v1/pessoas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(header().string("Location", "http://localhost/v1/pessoas/" + CANDIDATO_ID))
				.andExpect(status().isCreated());
		
	}
	
}

