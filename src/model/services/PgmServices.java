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
	
	public void saveOrUpdate(Pgm obj) {
		if (obj.getId() == null) {
			pgmDao.insert(obj);
		}else {
			pgmDao.update(obj);
		}
	}

}
