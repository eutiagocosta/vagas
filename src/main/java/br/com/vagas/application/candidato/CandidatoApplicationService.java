package br.com.vagas.application.candidato;

import org.springframework.stereotype.Service;

import br.com.vagas.domain.Nivel;
import br.com.vagas.domain.candidato.Candidato;
import br.com.vagas.domain.candidato.CandidatoId;
import br.com.vagas.domain.candidato.CandidatoRepositorio;
import br.com.vagas.domain.candidato.Localizacao;

@Service
public class CandidatoApplicationService {

	private CandidatoRepositorio repositorio;

	public CandidatoApplicationService(CandidatoRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	public CandidatoId registrarCandidato(NovoCandidatoComando comando) {
		
		Candidato candidato = new Candidato(
				repositorio.novaIdentidade(), 
				comando.getNome(), 
				comando.getProfissao(),
				Localizacao.valor(comando.getLocalizacao()),
				Nivel.valor(comando.getNivel()));
		
		repositorio.salvar(candidato);
		
		//Publicar evento NovoCandidatoRegistradoEvento
		
		return candidato.candidatoId();
		
	}

}
