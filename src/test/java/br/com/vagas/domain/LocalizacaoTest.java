package br.com.vagas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.vagas.AbstractTest;
import br.com.vagas.domain.candidato.Localizacao;
import br.com.vagas.domain.exception.ArgumentoInvalidoException;

public class LocalizacaoTest extends AbstractTest {

	@Test
	public void obterNivelPorValor() {
		
		assertEquals(Localizacao.A, Localizacao.valor("A"));
		
	}
	
	@Test
	public void obterLocalizacaoPorValorDadoNaoEncontradoDeveLancarException() {
		
		assertThatThrownBy(() -> Localizacao.valor("W"))
			.hasMessage("Localização informado é inválido.")
			.isInstanceOf(ArgumentoInvalidoException.class);
		
	}
	
}
