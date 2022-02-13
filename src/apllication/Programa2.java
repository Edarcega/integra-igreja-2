package apllication;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.MembroDao;
import model.entities.Igreja;
import model.entities.Membro;
import model.entities.Pgm;
import model.entities.enums.StatusCivil;
import model.entities.enums.StatusMembro;
import model.entities.enums.StatusPgm;

public class Programa2 {
	static Scanner sc = new Scanner(System.in);
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) throws IOException, ParseException {

		Integer opc = null;
		while (opc == null || opc != 0) {
			System.out.println();
			System.out.println("---- Integra Igreja Menu ----");
			System.out.println("0 - Sair");
			System.out.println("1 - Cadastrar Membro ");
			System.out.println("2 - Perquisar Membro pelo ID ");
			System.out.println("3 - Perquisar Membros pelo ID do PGM ");
			System.out.println("4 - Consultar todos os membros ");
			System.out.println("5 - Pesquisar membros pela Igreja ");
			System.out.println("6 - Pesquisar membros pelo nome ");
			System.out.println("7 - Excluir membro pelo ID ");
			opc = sc.nextInt();
			sc.nextLine();
			executaTeste(opc);
		}

		sc.close();

	}

	public static void executaTeste(Integer opc) throws ParseException {
		MembroDao membroDao = DaoFactory.createMembroDao();
		Membro membro = new Membro();

		switch (opc) {
		case 1:
			Pgm pgm = new Pgm(9, "NÃO INTEGRADO", StatusPgm.ATIVO);
			Igreja ig = new Igreja(2, "IGREJA BATISTA MONTE CASTELO", "BATISTA", "27247539000105");
			sc.nextLine();

			System.out.println("Nome: ");
			membro.setNome(sc.nextLine());
			System.out.println("Data de nascimento (Exemplo: 01/01/1990): ");
			membro.setDataDeNascimento(sdf.parse(sc.nextLine()));
			System.out.println("Genero - M/F: ");
			membro.setGenero(sc.nextLine());
			System.out.println("e-mail: ");
			membro.setEmail(sc.nextLine());
			System.out.println("Endereço: ");
			membro.setEndereco(sc.nextLine());
			System.out.println("Bairro: ");
			membro.setBairro(sc.nextLine());
			System.out.println("Telefone: ");
			membro.setTelefone(sc.nextLine());
			System.out.println("Estado Civil: ");
			membro.setEstadoCivil(StatusCivil.valueOf(sc.nextLine()));
			System.out.println("NOME CONJUGE: ");
			membro.setConjuge(sc.nextLine());
			System.out.println("ID conjuge: ");
			membro.setIdConjuge(sc.nextInt());
			sc.nextLine();
			membro.setPgm(pgm);
			System.out.println("Nome Filhos separados por - : ");
			membro.setNomeFilho(sc.nextLine());
			System.out.println("Quantidade de filhos: ");
			membro.setQtdFilhos(sc.nextInt());
			sc.nextLine();
			System.out.println("ID filhos separados por - : ");
			membro.setIdFilhos(sc.nextLine());
			System.out.println("RG ");
			membro.setRg(sc.nextLine());
			System.out.println("CPF: ");
			membro.setCpf(sc.nextLine());
			membro.setStatus(StatusMembro.ATIVO);
			System.out.println("Estado Civil: ");
			membro.setIgreja(ig);
			membroDao.insert(membro);
			System.out.println("Membro criado:" + "\n" + "ID: " + membro.getId() + "\n" + "Nome: " + membro.getNome());

			break;
		case 2:
			System.out.println(" ----  Buscar membro pelo ID  ---- ");
			System.out.println("Digite o ID do membro");
			int id = sc.nextInt();
			membro = membroDao.findById(id);
			System.out.println(membro);
			System.out.println();
			break;
		case 3:
			System.out.println(" ----  Buscar membroS pelo PGM  ---- ");
			System.out.println("ID PGM: ");
			List<Membro> membros = membroDao.findByPGM(sc.nextInt());
			sc.nextLine();

			for (Membro memb : membros) {
				System.out.println(memb);
			}

		case 4:
			System.out.println(" ----  Buscar todos os membros  ---- ");
			
			List<Membro> lmembros = membroDao.findAll();
			for (Membro memb : lmembros) {
				System.out.println(memb);
			}
			break;
		case 5:
			System.out.println(" ----  Buscar membros pela Igreja  ---- ");
			System.out.println("ID IGREJA: ");
			membros = membroDao.findByIgreja(sc.nextInt());
			sc.nextLine();

			for (Membro memb : membros) {
				System.out.println(memb);
			}
			break;
		case 6:
			System.out.println(" ----  Buscar membros pela nome  ---- ");
			System.out.println("Nome: ");
			String nome = sc.nextLine();
			membros = membroDao.findByName(nome);
			
			if (membros.size() == 0) {
				System.out.println("Nome não encontrado no bando de dados");
			}

			for (Membro memb : membros) {
				System.out.println();
				System.out.println("ID: " + memb.getId());
				System.out.println("Nome: " + memb.getNome());
			}
			break;
		case 7:
			System.out.println(" ----  Excluir Membro pelo ID  ---- ");
			System.out.println("ID: ");
			id = sc.nextInt();
			membroDao.deleteById(id);
			
			break;

		}
	}

}
