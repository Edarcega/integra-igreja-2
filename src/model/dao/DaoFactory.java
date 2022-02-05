package model.dao;

import model.dao.impl.IgrejaDaoJDBC;
import model.dao.impl.MembroDaoJDBC;
import model.dao.impl.PgmDaoJDBC;

public class DaoFactory {
	
	public static MembroDao createMembroDao () {
		return new MembroDaoJDBC();
	}
	
	public static IgrejaDao createIgrejaDao () {
		return new IgrejaDaoJDBC();
	}
	
	public static PgmDao createPgmDao () {
		return new PgmDaoJDBC();
	}

}
