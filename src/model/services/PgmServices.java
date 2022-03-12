package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.PgmDao;
import model.entities.Pgm;

public class PgmServices {
	
	PgmDao pgmDao = DaoFactory.createPgmDao();
	
	public List<Pgm> listPgms (){
		return pgmDao.findAll();
	}

}
