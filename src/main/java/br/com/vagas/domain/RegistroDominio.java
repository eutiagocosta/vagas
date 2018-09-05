package br.com.vagas.domain;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import br.com.vagas.domain.candidato.CandidatoRepositorio;

@Component
public class RegistroDominio implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	private static CandidatoRepositorio candidatoRepositorio;

	public static CandidatoRepositorio candidatoRepositorio() {
		if (candidatoRepositorio == null) {

			if (applicationContext == null)
				throw new RuntimeException("ApplicationContext não foi definido.");

			candidatoRepositorio = (CandidatoRepositorio) applicationContext.getBean(CandidatoRepositorio.class);
		}
		return candidatoRepositorio;
	}
	
	public static void defineCandidatoRepositorio(CandidatoRepositorio candidatoRepositorio) {
		RegistroDominio.candidatoRepositorio = candidatoRepositorio;
	}

	@Override
	public synchronized void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		RegistroDominio.applicationContext = applicationContext;
	}

}
