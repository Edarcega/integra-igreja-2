package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Igreja implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String denominacao;
	private String cnpj;

	public Igreja() {

	}

	public Igreja(Integer id, String nome, String denominacao, String cnpj) {
		super();
		this.id = id;
		this.nome = nome;
		this.denominacao = denominacao;
		this.cnpj = cnpj;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Igreja other = (Igreja) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return nome;
	}

}
