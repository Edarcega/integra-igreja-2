package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Membro implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Date dataDeNascimento;
	private String genero;
	private String email;
	private String endereco;
	private String bairro;
	private String telefone;
	private String conjuge;
	private Pgm pgm;
	private String filhos;
	private String rg;
	private String cpf;
	private Igreja igreja;

	public Membro() {

	}

	public Membro(Integer id, String nome, Date dataDeNascimento, String genero, String email, String endereco,
			String bairro, String telefone, String conjuge, Pgm pgm, String filhos, String rg, String cpf, Igreja igreja) {
		this.id = id;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.genero = genero;
		this.email = email;
		this.endereco = endereco;
		this.bairro = bairro;
		this.telefone = telefone;
		this.conjuge = conjuge;
		this.pgm = pgm;
		this.filhos = filhos;
		this.rg = rg;
		this.cpf = cpf;
		this.igreja = igreja;
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

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getConjuge() {
		return conjuge;
	}

	public void setConjuge(String conjuge) {
		this.conjuge = conjuge;
	}

	public Pgm getPgm() {
		return pgm;
	}

	public void setPgm(Pgm pgm) {
		this.pgm = pgm;
	}

	public String getFilhos() {
		return filhos;
	}

	public void setFilhos(String filhos) {
		this.filhos = filhos;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Igreja getIgreja() {
		return igreja;
	}

	public void setIgreja(Igreja igreja) {
		this.igreja = igreja;
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
		Membro other = (Membro) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Membro [id=" + id + ", nome=" + nome + ", dataDeNascimento=" + dataDeNascimento + ", genero=" + genero
				+ ", email=" + email + ", endereco=" + endereco + ", bairro=" + bairro + ", telefone=" + telefone
				+ ", conjuge=" + conjuge + ", pgm=" + pgm + ", filhos=" + filhos + ", rg=" + rg + ", cpf=" + cpf
				+ ", igreja=" + igreja + "]";
	}
	
	

}
