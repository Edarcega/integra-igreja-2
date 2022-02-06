package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.MembroDao;
import model.entities.Igreja;
import model.entities.Membro;
import model.entities.Pgm;
import model.entities.enums.StatusPgm;

public class MembroDaoJDBC implements MembroDao {

	private Connection conn;

	public MembroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Membro obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Membro obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Membro findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT *from tb_membros " + "INNER JOIN tb_igreja INNER JOIN tb_pgms "
					+ "ON tb_membros.ID_IGREJA = tb_igreja.ID and tb_membros.ID_PGM = tb_pgms.id "
					+ "WHERE tb_membros.id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Igreja ig = new Igreja();
				ig.setId(rs.getInt(15));
				ig.setNome(rs.getString(17));
				ig.setCnpj(rs.getString("CNPJ"));
				ig.setDenominacao(rs.getString("DENOMINAÇÃO"));

				Pgm pgm = new Pgm();
				pgm.setId(rs.getInt("ID_PGM"));
				pgm.setNome(rs.getString("NOME_PGM"));
				pgm.setStatus(StatusPgm.valueOf(rs.getString(22)));

				Membro membro = new Membro();
				membro.setId(rs.getInt("ID"));
				membro.setNome(rs.getString(2));
				membro.setDataDeNascimento(rs.getDate("DATA_DE_NASCIMENTO"));
				membro.setGenero(rs.getString("GENERO"));
				membro.setEmail(rs.getString("EMAIL"));
				membro.setEndereco(rs.getString("ENDEREÇO"));
				membro.setBairro(rs.getString("BAIRRO"));
				membro.setTelefone(rs.getString("TELEFONE"));
				membro.setConjuge(rs.getString("CONJUGE"));
				membro.setPgm(pgm);
				membro.setFilhos(rs.getString("FILHOS"));
				membro.setRg(rs.getString("RG"));
				membro.setCpf(rs.getString("CPF"));
				membro.setIgreja(ig);
				return membro;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public Membro findByName(String Name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Membro> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
