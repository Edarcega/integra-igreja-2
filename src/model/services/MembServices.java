package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.MembroDao;
import model.entities.Membro;

public class MembServices {

	MembroDao membroDao = DaoFactory.createMembroDao();

	public List<Membro> listaMembros() {
		return membroDao.findAll();
	}

	public void saveOrUpdate(Membro obj) {
		if (obj.getId() == null) {
			membroDao.insert(obj);
		} else {
			membroDao.update(obj);
		}
	}

}
