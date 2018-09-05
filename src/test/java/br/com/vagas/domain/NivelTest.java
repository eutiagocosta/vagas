package br.com.vagas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.vagas.AbstractTest;
import br.com.vagas.domain.exception.ArgumentoInvalidoException;

public class NivelTest extends AbstractTest{

	@Test
	public void obterNivelPorValor() {
		
		assertEquals(Nivel.ESTAGIARIO, Nivel.valor(1));
		
	}
	
	@Test
	public void obterNivelPorValorDadoNaoEncontradoDeveLancarException() {
		
		assertThatThrownBy(() -> Nivel.valor(10))
			.hasMessage("Nível informado é inválido.")
			.isInstanceOf(ArgumentoInvalidoException.class);
		
	}
	
}
