package apllication;

import model.dao.DaoFactory;
import model.dao.MembroDao;
import model.entities.Membro;

public class Program {

	public static void main(String[] args) {

		MembroDao membroDao = DaoFactory.createMembroDao();
		
		System.out.println(" ----  Testes 1: Buscar membro pelo ID  ---- ");
		Membro membro = membroDao.findById(35);
		System.out.println(membro);

	}

}
