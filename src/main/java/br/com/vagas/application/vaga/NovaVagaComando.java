package br.com.vagas.application.vaga;

public class NovaVagaComando {

	private String empresa;
	private String titulo;
	private String descricao;
	private String localizacao;
	private Integer nivel;

	public NovaVagaComando(String empresa, String titulo, String descricao, String localizacao, Integer nivel) {
		this.empresa = empresa;
		this.titulo = titulo;
		this.descricao = descricao;
		this.localizacao = localizacao;
		this.nivel = nivel;
	}

	@SuppressWarnings("unused")
	private NovaVagaComando() {}

	public String getEmpresa() {
		return empresa;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((localizacao == null) ? 0 : localizacao.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		NovaVagaComando other = (NovaVagaComando) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
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
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NovaVagaComando [empresa=" + empresa + ", titulo=" + titulo + ", descricao=" + descricao
				+ ", localizacao=" + localizacao + ", nivel=" + nivel + "]";
	}
	
}
