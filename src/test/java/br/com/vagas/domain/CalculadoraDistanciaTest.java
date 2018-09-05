package br.com.vagas.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.vagas.AbstractTest;
import br.com.vagas.domain.candidato.Localizacao;

public class CalculadoraDistanciaTest extends AbstractTest {
	
	@Test
	public void estaEntre() {
		
		assertTrue(CalculadoraDistancia.estaEntre(0, 0, 5));
		assertTrue(CalculadoraDistancia.estaEntre(6, 6, 10));
		assertTrue(CalculadoraDistancia.estaEntre(20, 16, 20));
	}
	
	@Test
	public void estaEntreDadoQueNaoEstejaEntreDeveRetornarFalso() {
		
		assertFalse(CalculadoraDistancia.estaEntre(8, 0, 5));
		assertFalse(CalculadoraDistancia.estaEntre(25, 16, 20));
		
	}
	
	@Test
	public void calcularAateC() {
		
		final CalculadoraDistancia calculadora = new CalculadoraDistancia();
		
		final Integer distancia = calculadora.calcular(Localizacao.A, Localizacao.C);
		
		assertEquals(Integer.valueOf(50), distancia);
		
	}
	
	@Test
	public void calcularAateD() {
		
		final CalculadoraDistancia calculadora = new CalculadoraDistancia();
		
		final Integer distancia = calculadora.calcular(Localizacao.A, Localizacao.D);
		
		assertEquals(Integer.valueOf(75), distancia);
		
	}

	@Test
	public void calcularCateD() {
		
		final CalculadoraDistancia calculadora = new CalculadoraDistancia();
		
		final Integer distancia = calculadora.calcular(Localizacao.C, Localizacao.D);
		
		assertEquals(Integer.valueOf(75), distancia);
		
	}
	
	@Test
	public void calcularAateE() {
		
		final CalculadoraDistancia calculadora = new CalculadoraDistancia();
		
		final Integer distancia = calculadora.calcular(Localizacao.A, Localizacao.E);
		
		assertEquals(Integer.valueOf(25), distancia);
		
	}
	
	@Test
	public void calcularCeateF() {
		
		final CalculadoraDistancia calculadora = new CalculadoraDistancia();
		
		final Integer distancia = calculadora.calcular(Localizacao.C, Localizacao.F);
		
		assertEquals(Integer.valueOf(25), distancia);
		
	}
	
}
