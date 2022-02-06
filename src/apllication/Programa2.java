package apllication;

import java.io.IOException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.MembroDao;
import model.entities.Membro;

public class Programa2 {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		
		
		
		Integer opc = null;
		while (opc == null || opc != 0) {
			System.out.println();
			System.out.println("---- Integra Igreja Menu ----");
			System.out.println("0 - Sair");
			System.out.println("1 - Cadastrar Membro ");
			System.out.println("2 - Perquisar Membro pelo ID ");
			System.out.println("3 - Perquisar Membros pelo ID do PGM ");
			System.out.println("4 - Consultar todos os membros ");
			opc = sc.nextInt();
			executaTeste(opc);
		}

		sc.close();

	}

	public static void executaTeste(Integer opc) {
		switch (opc) {
		case 1:
			System.out.println(1);
			break;
		case 2:
			System.out.println(" ----  Testes 1: Buscar membro pelo ID  ---- ");
			MembroDao membroDao = DaoFactory.createMembroDao();
			System.out.println("Digite o ID do membro");
			int id = sc.nextInt();
			Membro membro = membroDao.findById(id);
			System.out.println(membro);
			System.out.println();
			break;
		case 3:
			
			
		}
	}

}
