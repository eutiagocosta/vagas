package br.com.vagas.port.adapters.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vagas.application.vaga.NovaCandidaturaComando;
import br.com.vagas.application.vaga.NovaVagaComando;
import br.com.vagas.application.vaga.VagaApplicationService;
import br.com.vagas.application.vaga.data.CandidaturaData;
import br.com.vagas.domain.VagaId;
import io.swagger.annotations.ApiOperation;

@RestController
public class VagaController {

	@Autowired
	private VagaApplicationService service;

	@PostMapping(value = "v1/vagas")
	@ApiOperation(value = "Endpoint responsavel pelo registro de vagas.")
	public ResponseEntity<Void> novaVaga(@RequestBody NovaVagaComando comando) {
		
		final VagaId vagaId = service.registrarVaga(comando);
		
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{vagaId}")
				.buildAndExpand(vagaId.id()).toUri()).build();
	}
	
	//TODO eu faria este endpoint /v1/vagas/{vagaId}/registrar-candidatura
	@PostMapping(value = "v1/candidaturas")
	@ApiOperation(value = "Endpoint responsavel pelo registro de nova candidatura para uma determinada vaga.")
	public ResponseEntity<Void> novaCandidatura(@RequestBody NovaCandidaturaComando comando){
		
		service.registrarCandidatura(comando);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "v1/vagas/{vagaId}/candidaturas/ranking")
	@ApiOperation(value = "Endpoint responsavel pela listagem de candidaturas para uma vaga.")
	public ResponseEntity<List<CandidaturaData>> listarCandidaturas(@PathVariable VagaId vagaId){
		return ResponseEntity.ok(service.listarCandidaturas(vagaId));
	}
	
}
