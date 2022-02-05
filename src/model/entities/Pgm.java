package model.entities;

import java.io.Serializable;
import java.util.Objects;

import model.entities.enums.StatusPgm;

public class Pgm implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private StatusPgm status;

	public Pgm() {

	}

	public Pgm(Integer id, String nome, StatusPgm status) {
		this.id = id;
		this.nome = nome;
		this.status = status;
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

	public StatusPgm getStatus() {
		return status;
	}

	public void setStatus(StatusPgm status) {
		this.status = status;
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
		Pgm other = (Pgm) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Pgm [id=" + id + ", nome=" + nome + ", status=" + status + "]";
	}
	
	
}
