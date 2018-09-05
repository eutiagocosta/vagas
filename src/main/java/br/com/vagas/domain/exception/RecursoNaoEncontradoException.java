package br.com.vagas.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 4270607812250128114L;

	public RecursoNaoEncontradoException(String msg) {
		super(msg);
	}
	
}
