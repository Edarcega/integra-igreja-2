package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.IgrejaDao;
import model.entities.Igreja;

public class IgrejaDaoJDBC implements IgrejaDao {

	private Connection conn;

	public IgrejaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private Igreja instanciarIgreja(ResultSet rs) throws SQLException {
		Igreja ig = new Igreja();
		ig.setId(rs.getInt("ID"));
		ig.setNome(rs.getString("NOME_IGREJA"));
		ig.setCnpj(rs.getString("CNPJ"));
		ig.setDenominacao(rs.getString("DENOMINAÇÃO"));
		return ig;
	}

	@Override
	public void insert(Igreja obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("insert into tb_igreja (NOME_IGREJA, DENOMINAÇÃO,CNPJ) " + "value (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setString(2, obj.getDenominacao());
			st.setString(3, obj.getCnpj());

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
	public void update(Igreja obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("update tb_igreja set NOME_IGREJA = ?, CNPJ = ? WHERE ID = ?;");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getCnpj());
			st.setInt(3, obj.getId());
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
			st = conn.prepareStatement("DELETE FROM tb_igreja WHERE Id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Igreja findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM IntegraIgreja.tb_igreja where id = ?;");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Igreja ig = instanciarIgreja(rs);

				return ig;
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
	public List<Igreja> findByName(String name) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Igreja> igrejas = new ArrayList<>();

		try {
			st = conn.prepareStatement(
					"SELECT *FROM IntegraIgreja.tb_igreja WHERE tb_igreja.NOME_IGREJA LIKE ? order by NOME_IGREJA;");
			st.setString(1, name + "%");
			rs = st.executeQuery();
			while (rs.next()) {

				Igreja ig = instanciarIgreja(rs);

				igrejas.add(ig);

			}

			return igrejas;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Igreja> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Igreja> igrejas = new ArrayList<>();

		try {
			st = conn.prepareStatement("SELECT * FROM IntegraIgreja.tb_igreja;");
			rs = st.executeQuery();
			while (rs.next()) {

				Igreja ig = instanciarIgreja(rs);

				igrejas.add(ig);

			}

			return igrejas;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
