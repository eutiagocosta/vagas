package br.com.vagas.port.adapters.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vagas.application.candidato.CandidatoApplicationService;
import br.com.vagas.application.candidato.NovoCandidatoComando;
import br.com.vagas.domain.candidato.CandidatoId;
import io.swagger.annotations.ApiOperation;

@RestController
public class CandidatoController {

	@Autowired
	private CandidatoApplicationService service;

	@PostMapping(value = "v1/pessoas")
	@ApiOperation(value = "Endpoint responsavel pelo registro de candidatos.")
	public ResponseEntity<Void> novoCandidato(@RequestBody NovoCandidatoComando comando) {
		
		final CandidatoId candidatoId = service.registrarCandidato(comando);
		
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{candidatoId}")
				.buildAndExpand(candidatoId.id()).toUri()).build();
	}
	
}
