package br.com.vagas.domain;

import java.util.Arrays;

import br.com.vagas.domain.exception.ArgumentoInvalidoException;

public enum Nivel {
	
	ESTAGIARIO(1),
	JUNIOR(2),
	PLENO(3),
	SENIOR(4),
	ESPECIALISTA(5);

	private Integer valor;
	
	private Nivel(Integer valor) {
		this.valor = valor;
	}

	public static Nivel valor(Integer valor) {
		return Arrays.stream(Nivel.values())
			.filter(n -> n.valor() == valor)
			.findFirst()
			.orElseThrow(() -> new ArgumentoInvalidoException("Nível informado é inválido."));

	}
	
	public Integer valor() {
		return valor;
	}
	
}
