package br.com.vagas.port.adapters.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.vagas.domain.Vaga;
import br.com.vagas.domain.VagaId;

public interface VagaRepositorioSpringData extends CrudRepository<Vaga, VagaId> {

	Optional<Vaga> findByVagaId(VagaId vagaId);

}
