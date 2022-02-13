package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import model.entities.enums.StatusCivil;
import model.entities.enums.StatusMembro;

public class Membro implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Date dataDeNascimento;
	private String genero;
	private String email;
	private String endereco;
	private String bairro;
	private String telefone;
	private StatusCivil estadoCivil;
	private String conjuge;
	private Integer idConjuge;
	private Pgm pgm;
	private String nomeFilho;
	private Integer qtdFilhos;
	private String idFilhos;
	private String rg;
	private String cpf;
	private StatusMembro status;
	private Igreja igreja;

	public Membro() {

	}

	public Membro(Integer id, String nome, Date dataDeNascimento, String genero, String email, String endereco,
			String bairro, String telefone, StatusCivil estadoCivil, String conjuge, Integer idConjuge, Pgm pgm,
			String nomeFilho, Integer qtdFilhos, String idFilhos, String rg, String cpf, StatusMembro status,
			Igreja igreja) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.genero = genero;
		this.email = email;
		this.endereco = endereco;
		this.bairro = bairro;
		this.telefone = telefone;
		this.estadoCivil = estadoCivil;
		this.conjuge = conjuge;
		this.idConjuge = idConjuge;
		this.pgm = pgm;
		this.nomeFilho = nomeFilho;
		this.qtdFilhos = qtdFilhos;
		this.idFilhos = idFilhos;
		this.rg = rg;
		this.cpf = cpf;
		this.status = status;
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

	public StatusCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(StatusCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getConjuge() {
		return conjuge;
	}

	public void setConjuge(String conjuge) {
		this.conjuge = conjuge;
	}

	public Integer getIdConjuge() {
		return idConjuge;
	}

	public void setIdConjuge(Integer idConjuge) {
		this.idConjuge = idConjuge;
	}

	public Pgm getPgm() {
		return pgm;
	}

	public void setPgm(Pgm pgm) {
		this.pgm = pgm;
	}

	public String getNomeFilho() {
		return nomeFilho;
	}

	public void setNomeFilho(String nomeFilho) {
		this.nomeFilho = nomeFilho;
	}

	public Integer getQtdFilhos() {
		return qtdFilhos;
	}

	public void setQtdFilhos(Integer qtdFilhos) {
		this.qtdFilhos = qtdFilhos;
	}

	public String getIdFilhos() {
		return idFilhos;
	}

	public void setIdFilhos(String idFilhos) {
		this.idFilhos = idFilhos;
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

	public StatusMembro getStatus() {
		return status;
	}

	public void setStatus(StatusMembro status) {
		this.status = status;
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
		return "Membro id=" + id + "\n" 
				+ "- nome=" + nome + "\n" 
				+ "- dataDeNascimento=" + dataDeNascimento + "\n"  
				+ "- genero=" + genero
				+ "- email=" + email + "\n" 
				+ "- endereco=" + endereco + "\n" 
				+ "- bairro=" + bairro + "\n" 
				+ "- telefone=" + telefone
				+ "- estadoCivil=" + estadoCivil + "\n" 
				+ "- conjuge=" + conjuge + "\n" 
				+ "- idConjuge=" + idConjuge + "\n" 
				+ "- pgm=" + pgm + "\n"
				+ "- nomeFilho=" + nomeFilho + "\n" 
				+ "- qtdFilhos=" + qtdFilhos + "\n" 
				+ "- idFilhos=" + idFilhos + "\n" 
				+ "- rg=" + rg + "\n" 
				+ "- cpf=" + cpf + "\n" 
				+ "- status=" + status + "\n" 
				+ "- igreja=" + igreja + "\n";
	}

}
