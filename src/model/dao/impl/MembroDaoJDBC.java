package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.MembroDao;
import model.entities.Igreja;
import model.entities.Membro;
import model.entities.Pgm;
import model.entities.enums.StatusCivil;
import model.entities.enums.StatusMembro;
import model.entities.enums.StatusPgm;

public class MembroDaoJDBC implements MembroDao {

	private Connection conn;

	public MembroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private Membro instanciarMembro(ResultSet rs, Igreja ig, Pgm pgm) throws SQLException {
		Membro membro = new Membro();
		membro.setId(rs.getInt("ID"));
		membro.setNome(rs.getString(2));
		membro.setDataDeNascimento(rs.getDate("DATA_DE_NASCIMENTO"));
		membro.setGenero(rs.getString("GENERO"));
		membro.setEmail(rs.getString("EMAIL"));
		membro.setEndereco(rs.getString("ENDEREÇO"));
		membro.setBairro(rs.getString("BAIRRO"));
		membro.setTelefone(rs.getString("TELEFONE"));
		membro.setEstadoCivil(StatusCivil.valueOf(rs.getString("ESTADO_CIVIL")));
		membro.setConjuge(rs.getString("NOME_CONJUGE"));
		membro.setIdConjuge(rs.getInt("ID_CONJUGE"));
		membro.setPgm(pgm);
		membro.setNomeFilho(rs.getString("NOME_FILHO"));
		membro.setQtdFilhos(rs.getInt("QTD_FILHOS"));
		membro.setIdFilhos(rs.getString("ID_FILHOS"));
		membro.setRg(rs.getString("RG"));
		membro.setCpf(rs.getString("CPF"));
		membro.setStatus(StatusMembro.valueOf(rs.getString("STATUS_MEMBRO")));
		membro.setIgreja(ig);
		return membro;
	}

	private Pgm instanciarPgm(ResultSet rs) throws SQLException {
		Pgm pgm = new Pgm();
		pgm.setId(rs.getInt("ID_PGM_PESSOA"));
		pgm.setNome(rs.getString("NOME_PGM"));
		pgm.setStatus(StatusPgm.valueOf(rs.getString("STATUS_PGM")));
		return pgm;
	}

	private Igreja instanciarIgreja(ResultSet rs) throws SQLException {
		Igreja ig = new Igreja();
		ig.setId(rs.getInt("ID_IGREJA_PESSOA"));
		ig.setNome(rs.getString("NOME_IGREJA"));
		ig.setCnpj(rs.getString("CNPJ"));
		ig.setDenominacao(rs.getString("DENOMINAÇÃO"));
		return ig;
	}

	@Override
	public void insert(Membro obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tb_membros "
					+ "(NOME_PESSOA,DATA_DE_NASCIMENTO,GENERO,EMAIL,ENDEREÇO,"
					+ "BAIRRO,TELEFONE,ESTADO_CIVIL,NOME_CONJUGE,ID_CONJUGE,ID_PGM_PESSOA,NOME_FILHO,QTD_FILHOS,ID_FILHOS,RG,CPF,STATUS_MEMBRO,ID_IGREJA_PESSOA) "
					+ "VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setDate(2, new java.sql.Date(obj.getDataDeNascimento().getTime()));
			st.setString(3, obj.getGenero());
			st.setString(4, obj.getEmail());
			st.setString(5, obj.getEndereco());
			st.setString(6, obj.getBairro());
			st.setString(7, obj.getTelefone());
			st.setString(8, obj.getEstadoCivil().toString());
			st.setString(9, obj.getConjuge());
			st.setInt(10, obj.getIdConjuge());
			st.setInt(11, obj.getPgm().getId());
			st.setString(12, obj.getNomeFilho());
			st.setInt(13, obj.getQtdFilhos());
			st.setString(14, obj.getIdFilhos());
			st.setString(15, obj.getRg());
			st.setString(16, obj.getCpf());
			st.setString(17, obj.getStatus().toString());
			st.setInt(18, obj.getIgreja().getId());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);

			} else {
				throw new DbException("Erro inesperado, dados não inseridos na base");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Membro obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("update tb_membros " + "SET NOME_PESSOA = ?, " + "EMAIL = ?, "
					+ "DATA_DE_NASCIMENTO = ?, " + "ID_PGM_PESSOA = ? " + "where ID = ? ");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getDataDeNascimento().getTime()));
			st.setInt(4, obj.getPgm().getId());
			st.setInt(5, obj.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM tb_membros WHERE Id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Membro findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT *from tb_membros " + "INNER JOIN tb_igreja INNER JOIN tb_pgms "
					+ "ON tb_membros.ID_IGREJA_PESSOA = tb_igreja.ID and tb_membros.ID_PGM_PESSOA = tb_pgms.id "
					+ "WHERE tb_membros.id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Igreja ig = instanciarIgreja(rs);

				Pgm pgm = instanciarPgm(rs);

				Membro membro = instanciarMembro(rs, ig, pgm);

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
	public List<Membro> findByName(String name) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT *from tb_membros INNER JOIN tb_igreja INNER JOIN tb_pgms ON tb_membros.ID_IGREJA_PESSOA = tb_igreja.ID and tb_membros.ID_PGM_PESSOA = tb_pgms.id\n"
							+ "WHERE tb_membros.NOME_PESSOA LIKE ? order by NOME_PESSOA;");

			st.setString(1, name + "%");

			rs = st.executeQuery();

			List<Membro> listMembros = new ArrayList<>();

			Map<Integer, Igreja> map = new HashMap<>();

			Map<Integer, Pgm> mapPgm = new HashMap<>();

			while (rs.next()) {

				Igreja ig = map.get(rs.getInt("ID_IGREJA_PESSOA"));
				Pgm pgm = mapPgm.get(rs.getInt("ID_PGM_PESSOA"));

				if (ig == null) {
					ig = instanciarIgreja(rs);
					map.put(rs.getInt("ID_IGREJA_PESSOA"), ig);
				}

				if (pgm == null) {
					pgm = instanciarPgm(rs);
					mapPgm.put(rs.getInt("ID_IGREJA_PESSOA"), pgm);
				}

				Membro membro = instanciarMembro(rs, ig, pgm);

				listMembros.add(membro);

				if (listMembros.size() == 0) {
					return null;
				}

			}

			return listMembros;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Membro> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT *from tb_membros " + "INNER JOIN tb_igreja INNER JOIN tb_pgms "
					+ "ON tb_membros.ID_IGREJA_PESSOA = tb_igreja.ID and tb_membros.ID_PGM_PESSOA = tb_pgms.id ");

			rs = st.executeQuery();

			List<Membro> listMembros = new ArrayList<>();

			Map<Integer, Igreja> mapIg = new HashMap<>();

			Map<Integer, Pgm> mapPg = new HashMap<>();

			while (rs.next()) {

				Igreja ig = mapIg.get(rs.getInt("ID_IGREJA_PESSOA"));

				if (ig == null) {
					ig = instanciarIgreja(rs);
					mapIg.put(rs.getInt("ID_IGREJA_PESSOA"), ig);
				}

				Pgm pgm = mapPg.get(rs.getInt("ID_PGM_PESSOA"));

				if (pgm == null) {
					pgm = instanciarPgm(rs);
					mapPg.put(rs.getInt("ID_PGM_PESSOA"), pgm);
				}

				Membro membro = instanciarMembro(rs, ig, pgm);

				listMembros.add(membro);

			}

			return listMembros;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Membro> findByPGM(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT *from tb_membros " + "INNER JOIN tb_igreja INNER JOIN tb_pgms "
					+ "ON tb_membros.ID_IGREJA_PESSOA = tb_igreja.ID and tb_membros.ID_PGM_PESSOA = tb_pgms.id "
					+ "WHERE tb_pgms.id = ? " + "order by tb_membros.NOME_PESSOA");
			st.setInt(1, id);

			rs = st.executeQuery();

			List<Membro> listMembros = new ArrayList<>();

			Map<Integer, Igreja> map = new HashMap<>();

			Map<Integer, Pgm> mapPgm = new HashMap<>();

			while (rs.next()) {

				// Arrumar a instanciação da igreja para pegar os nomes corretos
				Igreja ig = map.get(rs.getInt("ID_IGREJA_PESSOA"));
				Pgm pgm = mapPgm.get(rs.getInt("ID_PGM_PESSOA"));

				if (ig == null) {
					ig = instanciarIgreja(rs);
					map.put(rs.getInt("ID_IGREJA_PESSOA"), ig);
				}

				if (pgm == null) {
					pgm = instanciarPgm(rs);
					mapPgm.put(rs.getInt("ID_IGREJA_PESSOA"), pgm);
				}

				Membro membro = instanciarMembro(rs, ig, pgm);

				listMembros.add(membro);

			}

			return listMembros;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Membro> findByIgreja(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT *from tb_membros " + "INNER JOIN tb_igreja INNER JOIN tb_pgms "
					+ "ON tb_membros.ID_IGREJA_PESSOA = tb_igreja.ID and tb_membros.ID_PGM_PESSOA = tb_pgms.id "
					+ "WHERE tb_igreja.id = ? " + "order by tb_membros.NOME_PESSOA");
			st.setInt(1, id);

			rs = st.executeQuery();

			List<Membro> listMembros = new ArrayList<>();

			Map<Integer, Igreja> map = new HashMap<>();

			Map<Integer, Pgm> mapPgm = new HashMap<>();

			while (rs.next()) {

				Igreja ig = map.get(rs.getInt("ID_IGREJA_PESSOA"));
				Pgm pgm = mapPgm.get(rs.getInt("ID_PGM_PESSOA"));

				if (ig == null) {
					ig = instanciarIgreja(rs);
					map.put(rs.getInt("ID_IGREJA_PESSOA"), ig);
				}

				if (pgm == null) {
					pgm = instanciarPgm(rs);
					mapPgm.put(rs.getInt("ID_IGREJA_PESSOA"), pgm);
				}

				Membro membro = instanciarMembro(rs, ig, pgm);

				listMembros.add(membro);

			}

			return listMembros;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
