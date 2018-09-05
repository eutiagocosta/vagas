package br.com.vagas.domain.candidato;

import java.util.Optional;
import java.util.UUID;

public interface CandidatoRepositorio {

	default CandidatoId novaIdentidade() {
		return new CandidatoId(UUID.randomUUID().toString().toUpperCase());
	};

	void salvar(Candidato candidato);

	Optional<Candidato> obterPeloId(CandidatoId candidatoId);
	
}
