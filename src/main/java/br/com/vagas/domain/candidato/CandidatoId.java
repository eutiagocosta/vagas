package br.com.vagas.domain.candidato;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class CandidatoId implements Serializable{

	private static final long serialVersionUID = -1972743316823729571L;
	
	@Column(name="CANDIDATO_ID")
	private String id;
	
	public CandidatoId(String id) {
		setId(id);
	}
	
	@SuppressWarnings("unused")
	private CandidatoId() {}

	public String id() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CandidatoId other = (CandidatoId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}