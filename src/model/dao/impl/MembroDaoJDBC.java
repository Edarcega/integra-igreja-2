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
import model.entities.enums.StatusMembro;
import model.entities.enums.StatusPgm;

public class MembroDaoJDBC implements MembroDao {

	private Connection conn;

	public MembroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Membro obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tb_membros " + "(NOME,DATA_DE_NASCIMENTO,GENERO,EMAIL,ENDEREÇO,"
					+ "BAIRRO,TELEFONE,CONJUGE,ID_PGM,FILHOS,RG,CPF,STATUS,ID_IGREJA) " + "VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setDate(2, new java.sql.Date(obj.getDataDeNascimento().getTime()));
			st.setString(3, obj.getGenero());
			st.setString(4, obj.getEmail());
			st.setString(5, obj.getEndereco());
			st.setString(6, obj.getBairro());
			st.setString(7, obj.getTelefone());
			st.setString(8, obj.getConjuge());
			st.setInt(9, obj.getPgm().getId());
			st.setString(10, obj.getFilhos());
			st.setString(11, obj.getRg());
			st.setString(12, obj.getCpf());
			st.setString(13, obj.getStatus().toString());
			st.setInt(14, obj.getIgreja().getId());

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
			st = conn.prepareStatement("update tb_membros " + "SET NOME = ?, " + "EMAIL = ?, "
					+ "DATA_DE_NASCIMENTO = ?, " + "ID_PGM = ? " + "where ID = ? ");

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
		membro.setConjuge(rs.getString("CONJUGE"));
		membro.setPgm(pgm);
		membro.setFilhos(rs.getString("FILHOS"));
		membro.setRg(rs.getString("RG"));
		membro.setCpf(rs.getString("CPF"));
		membro.setStatus(StatusMembro.valueOf(rs.getString(14)));
		membro.setIgreja(ig);
		return membro;
	}

	private Pgm instanciarPgm(ResultSet rs) throws SQLException {
		Pgm pgm = new Pgm();
		pgm.setId(rs.getInt("ID_PGM"));
		pgm.setNome(rs.getString("NOME_PGM"));
		pgm.setStatus(StatusPgm.valueOf(rs.getString(22)));
		return pgm;
	}

	private Igreja instanciarIgreja(ResultSet rs) throws SQLException {
		Igreja ig = new Igreja();
		ig.setId(rs.getInt(15));
		ig.setNome(rs.getString(17));
		ig.setCnpj(rs.getString("CNPJ"));
		ig.setDenominacao(rs.getString("DENOMINAÇÃO"));
		return ig;
	}

	@Override
	public Membro findByName(String Name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Membro> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT *from tb_membros " + "INNER JOIN tb_igreja INNER JOIN tb_pgms "
					+ "ON tb_membros.ID_IGREJA = tb_igreja.ID and tb_membros.ID_PGM = tb_pgms.id ");

			rs = st.executeQuery();

			List<Membro> listMembros = new ArrayList<>();

			Map<Integer, Igreja> mapIg = new HashMap<>();

			Map<Integer, Pgm> mapPg = new HashMap<>();

			while (rs.next()) {

				Igreja ig = mapIg.get(rs.getInt("ID_IGREJA"));

				if (ig == null) {
					ig = instanciarIgreja(rs);
					mapIg.put(rs.getInt("ID_IGREJA"), ig);
				}

				Pgm pgm = mapPg.get(rs.getInt("ID_PGM"));

				if (pgm == null) {
					pgm = instanciarPgm(rs);
					mapPg.put(rs.getInt("ID_PGM"), pgm);
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
	public List<Membro> findByPGM(Pgm pgm) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT *from tb_membros " + "INNER JOIN tb_igreja INNER JOIN tb_pgms "
					+ "ON tb_membros.ID_IGREJA = tb_igreja.ID and tb_membros.ID_PGM = tb_pgms.id "
					+ "WHERE tb_pgms.id = ? " + "order by tb_membros.NOME");
			st.setInt(1, pgm.getId());

			rs = st.executeQuery();

			List<Membro> listMembros = new ArrayList<>();

			Map<Integer, Igreja> map = new HashMap<>();

			while (rs.next()) {

				// Arrumar a instanciação da igreja para pegar os nomes corretos
				Igreja ig = map.get(rs.getInt("ID_IGREJA"));

				if (ig == null) {
					ig = instanciarIgreja(rs);
					map.put(rs.getInt("ID_IGREJA"), ig);
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
	public List<Membro> findByIgreja(Igreja igreja) {
		// TODO Auto-generated method stub
		return null;
	}

}
