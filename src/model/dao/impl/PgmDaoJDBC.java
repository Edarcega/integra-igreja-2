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
import model.dao.PgmDao;
import model.entities.Pgm;
import model.entities.enums.StatusPgm;

public class PgmDaoJDBC implements PgmDao {

	private Connection conn;

	public PgmDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private Pgm instanciarPgm(ResultSet rs) throws SQLException {
		Pgm pgm = new Pgm();
		pgm.setId(rs.getInt("ID"));
		pgm.setNome(rs.getString("NOME_PGM"));
		pgm.setStatus(StatusPgm.valueOf(rs.getString("STATUS_PGM")));
		pgm.setIdIgreja(rs.getInt("ID_IGREJA_PGM"));
		return pgm;
	}

	@Override
	public void insert(Pgm obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("insert into tb_pgms (NOME_PGM,STATUS_PGM,ID_IGREJA_PGM) " + "value (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setString(2, obj.getStatus().toString());
			st.setInt(3, obj.getIdIgreja());

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
	public void update(Pgm obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("update tb_pgms set NOME_PGM = ?, STATUS_PGM = ? WHERE ID = ?;");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getStatus().toString());
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
			st = conn.prepareStatement("DELETE FROM tb_pgms WHERE Id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Pgm findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM IntegraIgreja.tb_pgms where id = ?;");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Pgm pgm = instanciarPgm(rs);

				return pgm;
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
	public List<Pgm> findByName(String name) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Pgm> pgms = new ArrayList<>();

		try {
			st = conn.prepareStatement(
					"SELECT *FROM IntegraIgreja.tb_pgms WHERE tb_pgms.NOME_PGM LIKE ? order by NOME_PGM;");
			st.setString(1, name + "%");
			rs = st.executeQuery();
			while (rs.next()) {

				Pgm pgm = instanciarPgm(rs);

				pgms.add(pgm);

			}

			return pgms;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Pgm> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Pgm> pgms = new ArrayList<>();

		try {
			st = conn.prepareStatement(
					"SELECT *FROM IntegraIgreja.tb_pgms order by NOME_PGM;");
			rs = st.executeQuery();
			while (rs.next()) {

				Pgm pgm = instanciarPgm(rs);

				pgms.add(pgm);

			}

			return pgms;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
