package br.com.vagas.port.adapters.repositorio;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.vagas.domain.candidato.Candidato;
import br.com.vagas.domain.candidato.CandidatoId;
import br.com.vagas.domain.candidato.CandidatoRepositorio;

@Repository
public class CandidatoRepositorioJpa implements CandidatoRepositorio{

	private CandidatoRepositorioSpringData repositorio;
	
	public CandidatoRepositorioJpa(CandidatoRepositorioSpringData repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public void salvar(Candidato candidato) {
		repositorio.save(candidato);
	}

	@Override
	public Optional<Candidato> obterPeloId(CandidatoId candidatoId) {
		return repositorio.findByCandidatoId(candidatoId);
	}

}
