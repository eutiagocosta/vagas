package br.com.vagas.application.candidato;

public class NovoCandidatoComando {

	private String nome;
	private String profissao;
	private String localizacao;
	private Integer nivel;

	public NovoCandidatoComando(String nome, String profissao, String localizacao, Integer nivel) {
		this.nome = nome;
		this.profissao = profissao;
		this.localizacao = localizacao;
		this.nivel = nivel;
	}
	
	@SuppressWarnings("unused")
	private NovoCandidatoComando() {}

	public String getNome() {
		return nome;
	}

	public String getProfissao() {
		return profissao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((localizacao == null) ? 0 : localizacao.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((profissao == null) ? 0 : profissao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NovoCandidatoComando other = (NovoCandidatoComando) obj;
		if (localizacao == null) {
			if (other.localizacao != null)
				return false;
		} else if (!localizacao.equals(other.localizacao))
			return false;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (profissao == null) {
			if (other.profissao != null)
				return false;
		} else if (!profissao.equals(other.profissao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NovoCandidatoComando [nome=" + nome + ", profissao=" + profissao + ", localizacao=" + localizacao
				+ ", nivel=" + nivel + "]";
	}
	
}
