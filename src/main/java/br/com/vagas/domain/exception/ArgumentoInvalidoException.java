package br.com.vagas.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ArgumentoInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 2570292314504160537L;

	public ArgumentoInvalidoException(String msg) {
		super(msg);
	}
	
}
