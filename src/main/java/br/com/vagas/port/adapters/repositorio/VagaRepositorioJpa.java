package br.com.vagas.port.adapters.repositorio;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.vagas.domain.Vaga;
import br.com.vagas.domain.VagaId;
import br.com.vagas.domain.VagaRepositorio;

@Repository
public class VagaRepositorioJpa implements VagaRepositorio {

	private VagaRepositorioSpringData repositorio;
	
	public VagaRepositorioJpa(VagaRepositorioSpringData repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public void salvar(Vaga vaga) {
		repositorio.save(vaga);
	}

	@Override
	public Optional<Vaga> obterPeloId(VagaId vagaId) {
		return repositorio.findByVagaId(vagaId);
	}

}
