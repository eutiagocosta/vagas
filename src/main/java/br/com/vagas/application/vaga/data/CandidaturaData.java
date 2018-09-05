package br.com.vagas.application.vaga.data;

public class CandidaturaData {

	private String nome;
	private String profissao;
	private String localizacao;
	private Integer nivel;
	private Integer score;

	public CandidaturaData(String nome, String profissao, String localizacao, Integer nivel, Integer score) {
		setNome(nome);
		setProfissao(profissao);
		setLocalizacao(localizacao);
		setNivel(nivel);
		setScore(score);
	}
	
	@SuppressWarnings("unused")
	private CandidaturaData() {}

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

	public Integer getScore() {
		return score;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}

	private void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	private void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	private void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	private void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "CandidaturaData [nome=" + nome + ", profissao=" + profissao + ", localizacao=" + localizacao
				+ ", nivel=" + nivel + ", score=" + score + "]";
	}
	
}
