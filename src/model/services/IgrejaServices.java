package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.IgrejaDao;
import model.entities.Igreja;

public class IgrejaServices {

	IgrejaDao igrejaDao = DaoFactory.createIgrejaDao();

	public List<Igreja> listaIgrejas() {
		return igrejaDao.findAll();
	}

	public void saveOrUpdate(Igreja obj) {
		if (obj.getId() == null) {
			igrejaDao.insert(obj);
		} else {
			igrejaDao.update(obj);
		}
	}

}
