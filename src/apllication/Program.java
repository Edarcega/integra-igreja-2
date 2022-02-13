package apllication;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.MembroDao;
import model.entities.Membro;
import model.entities.Pgm;
import model.entities.enums.StatusPgm;

public class Program {

	public static void main(String[] args) {

		MembroDao membroDao = DaoFactory.createMembroDao();

		//Está funcionando
		/*
		System.out.println(" ----  Testes 1: Buscar membro pelo ID  ---- ");
		Membro membro = membroDao.findById(35);
		System.out.println(membro);
		*/

		// Está funcionando
		/*
		System.out.println("\n ----  Testes 2: Buscar membro pelo PGM  ---- ");
		List<Membro> membros = membroDao.findByPGM(9);
		
		for (Membro memb : membros) {
			System.out.println(memb);
		}
		*/
		
		
		/* eSTÁ FUNCIONANDO
		System.out.println("\n ----  Testes 3: Buscar Todos  ---- ");
		List<Membro> lmembros = membroDao.findAll();
		
		for (Membro memb : lmembros) {
			System.out.println(memb);
		}
		*/
		
		
		/* Esta funcionando
		System.out.println("\n ----  Testes 4: Inserir dados no banco  ---- ");
		membro.setId(null);
		membro.setNome("Teste inserção de dados a partir do Java");
		membroDao.insert(membro);
		System.out.println(membro.getId());
		*/
		
		
		/*Esta funcionando
		 * System.out.println("\n ----  Testes 5: Update de dados no banco  ---- ");
		membro.setNome("Nome alterado pelo update");
		membro.setEmail("email@alterado.peloupdate.com");
		membro.setDataDeNascimento(new Date());
		Pgm pgm = new Pgm(9,"NÃO INTEGRADO", StatusPgm.ATIVO);
		membro.setPgm(pgm);
		membroDao.update(membro);
		System.out.println(membro);
		*/

	}

}
