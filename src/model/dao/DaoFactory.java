package model.dao;

import db.DB;
import model.dao.impl.IgrejaDaoJDBC;
import model.dao.impl.MembroDaoJDBC;
import model.dao.impl.PgmDaoJDBC;

public class DaoFactory {
	
	public static MembroDao createMembroDao () {
		return new MembroDaoJDBC(DB.getConnection());
	}
	
	public static IgrejaDao createIgrejaDao () {
		return new IgrejaDaoJDBC();
	}
	
	public static PgmDao createPgmDao () {
		return new PgmDaoJDBC();
	}

}
