package br.com.vagas.domain;

import java.util.Optional;
import java.util.UUID;

public interface VagaRepositorio {

	default VagaId novaIdentidade() {
		return new VagaId(UUID.randomUUID().toString().toUpperCase());
	}
	
	void salvar(Vaga vaga);

	Optional<Vaga> obterPeloId(VagaId vagaId);

}
