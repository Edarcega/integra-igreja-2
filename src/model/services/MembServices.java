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

}
