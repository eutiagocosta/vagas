package br.com.vagas.port.adapters.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.vagas.domain.candidato.Candidato;
import br.com.vagas.domain.candidato.CandidatoId;

public interface CandidatoRepositorioSpringData extends CrudRepository<Candidato, CandidatoId>{

	Optional<Candidato> findByCandidatoId(CandidatoId candidatoId);

}
