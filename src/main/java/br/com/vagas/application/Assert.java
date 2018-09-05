package br.com.vagas.application;

import br.com.vagas.domain.exception.ArgumentoInvalidoException;

public class Assert {

	public static void assertArgumentNotEmpty(String field, String errMessage) {
        if (field == null || field.isEmpty())
            throw new ArgumentoInvalidoException(errMessage);
    }
	
	public static void assertArgumentNotNull(Object obj, String errMessage) {
        if (obj == null)
            throw new ArgumentoInvalidoException(errMessage);
    }
	
}
