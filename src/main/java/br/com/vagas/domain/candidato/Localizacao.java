package br.com.vagas.domain.candidato;

import java.util.Arrays;

import br.com.vagas.domain.exception.ArgumentoInvalidoException;

public enum Localizacao {
	
	A("A"),
	B("B"),
	C("C"),
	D("D"),
	E("E"),
	F("F");

	private String valor;
	
	private Localizacao(String valor) {
		this.valor = valor;
	}

	public static Localizacao valor(String valor) {
		return Arrays.stream(Localizacao.values())
			.filter(l -> l.valor().equals(valor))
			.findFirst()
			.orElseThrow(() -> new ArgumentoInvalidoException("Localização informado é inválido."));

	}
	
	public String valor() {
		return valor;
	}

}
