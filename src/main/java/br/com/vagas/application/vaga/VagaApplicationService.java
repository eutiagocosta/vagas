package br.com.vagas.application.vaga;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.vagas.application.vaga.data.CandidaturaData;
import br.com.vagas.domain.Nivel;
import br.com.vagas.domain.Vaga;
import br.com.vagas.domain.VagaId;
import br.com.vagas.domain.VagaRepositorio;
import br.com.vagas.domain.candidato.Localizacao;
import br.com.vagas.domain.exception.RecursoNaoEncontradoException;

@Service
public class VagaApplicationService {

	public static final String ERR_VAGA_INVALIDA = "Vaga informada não encontrada.";
	
	private VagaRepositorio repositorio;

	public VagaApplicationService(VagaRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	public VagaId registrarVaga(NovaVagaComando comando) {
		
		Vaga vaga = new Vaga(repositorio.novaIdentidade(),
				comando.getEmpresa(),
				comando.getTitulo(),
				comando.getDescricao(),
				Localizacao.valor(comando.getLocalizacao()),
				Nivel.valor(comando.getNivel()));
		
		repositorio.salvar(vaga);
		
		return vaga.vagaId();
	}

	public void registrarCandidatura(NovaCandidaturaComando comando) {
		
		Vaga vaga = repositorio.obterPeloId(comando.getVagaId())
				.orElseThrow(() -> new RecursoNaoEncontradoException(ERR_VAGA_INVALIDA));
		
		vaga.registrarCandidatura(comando.getCandidatoId());
		
		repositorio.salvar(vaga);
		
	}

	public List<CandidaturaData> listarCandidaturas(VagaId vagaId) {
		
		Vaga vaga = repositorio.obterPeloId(vagaId)
				.orElseThrow(() -> new RecursoNaoEncontradoException(ERR_VAGA_INVALIDA));
		
		final List<CandidaturaData> candidaturas = vaga.candidatos()
				.stream()
				.sorted((c1, c2) -> c2.score().compareTo(c1.score()))
				.map(candidato -> new CandidaturaData(candidato.nome(),
						candidato.profissao(),
						candidato.localizacao().valor(),
						candidato.nivel().valor(),
						candidato.score()))
				.collect(Collectors.toList());

		return candidaturas;
		
	}

}
