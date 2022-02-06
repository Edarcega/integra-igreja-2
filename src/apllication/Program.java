package apllication;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.MembroDao;
import model.entities.Membro;
import model.entities.Pgm;
import model.entities.enums.StatusPgm;

public class Program {

	public static void main(String[] args) {

		MembroDao membroDao = DaoFactory.createMembroDao();

	
		System.out.println(" ----  Testes 1: Buscar membro pelo ID  ---- ");
		Membro membro = membroDao.findById(35);
		System.out.println(membro);

		/*
		System.out.println("\n ----  Testes 2: Buscar membro pelo PGM  ---- ");
		Pgm pgm = new Pgm(1, "ALFA", StatusPgm.ATIVO);
		List<Membro> membros = membroDao.findByPGM(pgm);
		
		for (Membro memb : membros) {
			System.out.println(memb);
		}
		*/
		
		/*
		System.out.println("\n ----  Testes 3: Buscar Todos  ---- ");
		List<Membro> lmembros = membroDao.findAll();
		
		for (Membro memb : lmembros) {
			System.out.println(memb);
		}
		*/
		
		
		System.out.println("\n ----  Testes 4: Inserir dados no banco  ---- ");
		membro.setId(null);
		membro.setNome("Teste inserção de dados a partir do Java");
		membroDao.insert(membro);
		System.out.println(membro.getId());

	}

}
