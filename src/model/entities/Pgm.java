package model.entities;

import java.io.Serializable;
import java.util.Objects;

import model.enums.Status;

public class Pgm implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Status status;

	public Pgm() {

	}

	public Pgm(Integer id, String nome, Status status) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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
